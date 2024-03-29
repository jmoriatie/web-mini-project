package com.api.miniproject.controller.item;

import com.api.miniproject.domain.Account;
import com.api.miniproject.domain.Item;
import com.api.miniproject.dto.item.ItemSaveDto;
import com.api.miniproject.dto.item.ItemUpdateDto;
import com.api.miniproject.service.item.ItemService;
import com.api.miniproject.util.page.PageResolver;
import com.api.miniproject.util.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService service;
    private final ConversionService conversionService;

    @GetMapping("/add")
    public String saveItemForm(Model model) {
        model.addAttribute("item", new ItemSaveDto());
        return "item/addForm";
    }

    @PostMapping("/add")
    public String saveItem(@Validated @ModelAttribute("item") ItemSaveDto itemSaveDto, BindingResult bindingResult,
                           RedirectAttributes redirectAttributes, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "item/addForm";
        }

        Item saveItem = conversionService.convert(itemSaveDto, Item.class); // 정상 로직 start, 컨버팅

//        Long accountId = ((Account) request.getSession(false).getAttribute(SessionConst.LOGIN_ACCOUNT)).getId(); // 유저아이디 꺼내서 저장

        UserDetails userDetails = getUserDetails();

        saveItem.setAccountId(userDetails.getUsername()); // foreign key

        Item savedItem = service.saveItem(saveItem);// service 저장

        redirectAttributes.addAttribute("search-item", savedItem.getId());
        redirectAttributes.addAttribute("saveStatus", true);
        log.info("아이템 저장 item={}", savedItem);

        return "redirect:/item/item";
    }

    @GetMapping("/items")
    public String findAllItems() {
        return "item/items";
    }

    // ajax ITEM LIST 반환 controller
    @GetMapping("/itemList")
    public String sendItemTest(@RequestParam(defaultValue = "") String keyword,
                               @RequestParam(defaultValue = "1") Integer page,
                               HttpServletRequest request,
                               Model model) {

        UserDetails userDetails = getUserDetails();
        String username = userDetails.getUsername();

        log.info("username!!={}", username); // 검색했을 시 검색한 List 반환
        List<Item> items = searchListByItemName(keyword, service.findUserItems(username));
        items = searchListByItemName(keyword, items);

        int itemsCount = items.size(); // 전체 item size
        items = PageResolver.setSinglePageItemList(page, items); // 검색+페이지 조건 만족한 List -> 10개씩 출력
        int[] pages = PageResolver.getPageNumberingArray(itemsCount); // item PageCount 가져오기

        model.addAttribute("nullStatus", items.isEmpty()); // 아이템 없음
        model.addAttribute("normalStatus", !items.isEmpty()); // 아이템 있음

        model.addAttribute("items", items);
        model.addAttribute("pages", pages);
        model.addAttribute("keyword", keyword); // 검색값도 담아서, 검색 리스트도 출력
        model.addAttribute("itemsLength", itemsCount);

        return "/item/itemList";
    }

    private UserDetails getUserDetails() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        return userDetails;
    }

    // 검색기능
    private List<Item> searchListByItemName(String keyword, List<Item> userItems) {

        List<Item> searchItems = new ArrayList<>();
        if (keyword.isEmpty()) {
            searchItems = userItems;
        } else {
            for (Item item : userItems) {
                if (item.getItemName().contains(keyword)) {
                    searchItems.add(item);
                }
            }
        }
        return searchItems;
    }


    /***
     * id, itemName 별도의 find 메서드 호출
     */
    @GetMapping("/item")
    public String selectFindItemMethod(@RequestParam(name = "search-item") Long searchItem, Model model, @RequestHeader("host") String hostUrl) {
        Item findItem = getSearchItem(searchItem);

        if (findItem == null) {
            log.debug("redirect hostUrl={}", hostUrl);
            return "redirect:" + hostUrl;
        }
        model.addAttribute("item", findItem);

        return "item/item";
    }

    private Item getSearchItem(Long searchItem) {
        Item findItem;
        try {
            log.debug("itemId={}", searchItem);
            findItem = service.findById(searchItem);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("잘못된 아이템 아이디");
        }
        return findItem;
    }


    private Item findByName(String itemName) {
        Item findItem = service.findByName(itemName);
        log.debug("findByName={}", itemName);

        return findItem;
    }

    @GetMapping("/{itemId}/edit")
    public String updateItemForm(@PathVariable Long itemId, Model model) {
        Item findItem = service.findById(itemId);
        model.addAttribute("item", findItem);
        return "item/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String updateItem(@PathVariable Long itemId,
                             @Validated @ModelAttribute("item") ItemUpdateDto updateDto, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "item/editForm";
        }

        Item updatedItem = conversionService.convert(updateDto, Item.class); // 아이템 셋팅

        service.updateItem(itemId, updatedItem); //업데이트

        log.info("업데이트된 item={}", updatedItem); // 검증 필요
        redirectAttributes.addAttribute("search-item", itemId);
        redirectAttributes.addAttribute("updateStatus", true);

        return "redirect:/item/item";
    }

    @GetMapping("/{itemId}/delete")
    public String deleteItem(@PathVariable Long itemId, Model model, HttpServletRequest request) {

//        HttpSession session = request.getSession(false);
//        Account accountSession = (Account) session.getAttribute(SessionConst.LOGIN_ACCOUNT);

        UserDetails userDetails = getUserDetails();
        String accountId = userDetails.getUsername();

        Item findItem = service.findById(itemId);

        // 아이템 없거나 || 세션의 아이디와 아이템 주인이 다를 때
        if (findItem == null || !Objects.equals(accountId, findItem.getAccountId())) {
            return "redirect:/item/items";
        }

        service.deleteItem(itemId);
        log.info("delete item={}", findItem);

        model.addAttribute("deleteId", itemId);

        return "item/delete";
    }

}

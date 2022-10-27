package com.api.miniproject.controller;

import com.api.miniproject.domain.Item;
import com.api.miniproject.domain.User;
import com.api.miniproject.dto.item.ItemSaveDto;
import com.api.miniproject.dto.item.ItemUpdateDto;
import com.api.miniproject.service.item.ItemService;
import com.api.miniproject.util.page.PageResolver;
import com.api.miniproject.util.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
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

    @Autowired
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

        log.info("objectName={}", bindingResult.getObjectName());
        log.info("target={}", bindingResult.getTarget());

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "item/addForm";
        }

        // 정상 로직 start
        // 컨버팅
        Item saveItem = conversionService.convert(itemSaveDto, Item.class);

        // 유저아이디 꺼내서 저장
        Long userId = ((User) request.getSession(false).getAttribute(SessionConst.LOGIN_USER)).getId();
        saveItem.setUserId(userId); // foreign key

        // service 저장
        Item savedItem = service.saveItem(saveItem);

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

        Long id = ((User) request.getSession().getAttribute(SessionConst.LOGIN_USER)).getId();

        log.info("userId!!={}", id); // 검색했을 시 검색한 List 반환
        List<Item> items = searchListByItemName(keyword, service.findUserItems(id));
        items = searchListByItemName(keyword, items);

        log.info("keyword={}", keyword);
        log.info("items size={}", items.size());

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
    public String selectFindItemMethod(@RequestParam(name = "search-item") String searchItem, Model model, @RequestHeader("host") String hostUrl) {
        Item findItem = getSearchItem(searchItem);

        if (findItem == null) {
            log.debug("redirect hostUrl={}", hostUrl);
            return "redirect:" + hostUrl;
        }
        model.addAttribute("item", findItem);

        return "item/item";
    }

    private Item getSearchItem(String searchItem) {
        Item findItem;
        try {
            Long itemId = Long.parseLong(searchItem);
            log.debug("itemId={}", itemId);
            findItem = service.findById(itemId);
        } catch (NumberFormatException e) {
            findItem = findByName(searchItem);
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

        HttpSession session = request.getSession(false);
        User userSession = (User) session.getAttribute(SessionConst.LOGIN_USER);

        Item findItem = service.findById(itemId);

        // 아이템 없거나 || 세션의 아이디와 아이템 주인이 다를 때
        if (findItem == null || !Objects.equals(userSession.getId(), findItem.getUserId())) {
            return "redirect:/item/items";
        }

        service.deleteItem(itemId);
        log.info("delete item={}", findItem);

        // TODO : 삭제하시겠습니까? 확인 화면 출력
        model.addAttribute("deleteId", itemId);

        return "item/delete";
    }

}

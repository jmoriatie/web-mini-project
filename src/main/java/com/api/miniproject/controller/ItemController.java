package com.api.miniproject.controller;

import com.api.miniproject.domain.Item;
import com.api.miniproject.domain.User;
import com.api.miniproject.dto.item.ItemSaveDto;
import com.api.miniproject.dto.item.ItemUpdateDto;
import com.api.miniproject.service.item.ItemService;
import com.api.miniproject.util.session.SessionConst;
import com.api.miniproject.util.session.SessionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService service;
    private final ConversionService conversionService;

    @GetMapping("/add")
    public String saveItemForm(Model model){
        model.addAttribute("item", new Item());
        return "item/addForm";
    }

    @PostMapping("/add")
    public String saveItem(@Validated @ModelAttribute("item") ItemSaveDto itemSaveDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

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
        Long userId = SessionUtil.getUserIdFromSession();
        saveItem.setUserId(userId); // foreign key

        // service 저장
        Item savedItem = service.saveItem(saveItem);

        redirectAttributes.addAttribute("search-item", savedItem.getId());
        redirectAttributes.addAttribute("saveStatus", true);
        log.info("아이템 저장 item={}", savedItem);

        return "redirect:/item/item";
    }

    @GetMapping("/items")
    public String findAll(Model model, HttpServletRequest request) {
        Long id = ((User) request.getSession().getAttribute(SessionConst.LOGIN_USER)).getId();
        List<Item> items = service.findUserItems(id);

        if(items.size() == 0){
            model.addAttribute("nullStatus", true);
        }
        log.debug("Item List 개수: {}개", items.size());
        model.addAttribute("items", items);
        return "item/items";
    }

    /***
     * id, itemName 별도의 find 메서드 호출
     */
    @GetMapping("/item")
    public String selectFindItemMethod(@RequestParam(name = "search-item") String searchItem, Model model, @RequestHeader("host") String hostUrl ) {
         Item findItem = getSearchItem(searchItem);

        if(findItem == null){
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
    public String updateItem(@PathVariable Long itemId, @Validated @ModelAttribute("item")ItemUpdateDto updateDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "item/editForm";
        }

        // 아이템 셋팅
        Item updatedItem = service.findById(itemId);

        updatedItem.setItemName(updateDto.getItemName());
        updatedItem.setPrice(updateDto.getPrice());
        updatedItem.setQuantity(updateDto.getQuantity());
        updatedItem.setBuyUrl(updateDto.getBuyUrl());

        //업데이트
        service.updateItem(itemId, updatedItem);

        // 검증 필요
        log.info("업데이트된 item={}", updatedItem);
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
        if(findItem == null || userSession.getId() != findItem.getUserId()){
            return "redirect:/item/items";
        }

        service.deleteItem(itemId);
        log.info("delete item={}", findItem);

        // TODO : 삭제하시겠습니까? 확인 화면 출력
        model.addAttribute("deleteId", itemId);

        return "item/delete";
    }
}

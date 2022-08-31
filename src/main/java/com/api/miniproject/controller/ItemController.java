package com.api.miniproject.controller;

import com.api.miniproject.domain.Item;
import com.api.miniproject.dto.ItemSaveDto;
import com.api.miniproject.dto.ItemUpdateDto;
import com.api.miniproject.service.ItemService;
import com.api.miniproject.util.session.SessionUtil;
import com.api.miniproject.util.validation.ItemValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService service;
//    private final ItemValidator itemValidator;
//
//    // 이 controller 부를 때마다 WebDataBinder 만들어지고, 거기다가 Validator를 넣어놓음
//    @InitBinder
//    public void init(WebDataBinder dataBinder){
//        dataBinder.addValidators(itemValidator);
//    }

    @GetMapping("add")
    public String saveItemForm(Model model){
        model.addAttribute("item", new Item());
        return "item/addForm";
    }

    @PostMapping("add")
    public String saveItem(@Validated @ModelAttribute("item") ItemSaveDto itemSaveDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        log.info("objectName={}", bindingResult.getObjectName());
        log.info("target={}", bindingResult.getTarget());

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "item/addForm";
        }

        // 정상 로직
        Long userId = SessionUtil.getUserIdFromSession();
        itemSaveDto.setUserId(userId); // foreign key

        Item saveItem = new Item(itemSaveDto);
        Item savedItem = service.saveItem(saveItem);

        redirectAttributes.addAttribute("search-item", savedItem.getId());
        redirectAttributes.addAttribute("saveStatus", true);
        log.info("저장된 item={}", savedItem);

        return "redirect:/item/item";
    }

    @GetMapping("items")
    public String findAll(Model model) {
        Long userId = SessionUtil.getUserIdFromSession();
        List<Item> items = service.findUserItems(userId);

        if(items.size() == 0){
            model.addAttribute("nullStatus", true);
        }
        log.debug("Item List 개수: {}개", items.size());
        model.addAttribute("items", items);
        return "item/items";
    }

    /***
     * id, itemName 별도의 find 메서드 호출
     * TODO : 개별 아이템에 대한 처리는 interceptor 에서 안하나?
     */
    @GetMapping("item")
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

    @GetMapping("{itemId}/edit")
    public String updateItemForm(@PathVariable Long itemId, Model model) {
        Item findItem = service.findById(itemId);
        model.addAttribute("item", findItem);
        return "item/editForm";
    }

    @PostMapping("{itemId}/edit")
    public String updateItem(@PathVariable Long itemId, @Validated @ModelAttribute("item")ItemUpdateDto updateDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "item/editForm";
        }

        Item updateItem = new Item(updateDto);

        service.updateItem(itemId, updateItem);
        // 검증 필요
        log.info("업데이트된 item={}", updateItem);
        redirectAttributes.addAttribute("search-item", updateItem.getId());
        redirectAttributes.addAttribute("updateStatus", true);

        return "redirect:/item/item";
    }

    @GetMapping("{itemId}/delete")
    public String deleteItem(@PathVariable Long itemId, RedirectAttributes redirectAttributes) {
        service.deleteItem(itemId);
        redirectAttributes.addAttribute("deleteStatus", true);
        redirectAttributes.addAttribute("deleteId", itemId);

        return "redirect:/item/delete";
    }

    @PostConstruct
    public void setTestItem() {
        Item item1 = new Item("itemA", 30000, 100, "www.test1.com", 1L);
        Item item2 = new Item("itemB", 50000, 75, "www.test2.com", 1L);
        Item item3 = new Item("itemC", 100000, 50, "www.test3.com", 2L);
        service.saveItem(item1);
        service.saveItem(item2);
        service.saveItem(item3);
        log.debug("테스트 아이템 생성={}", item1.toString());
        log.debug("테스트 아이템 생성={}", item2.toString());
        log.debug("테스트 아이템 생성={}", item3.toString());
    }
}

package com.api.miniproject.controller;

import com.api.miniproject.domain.Item;
import com.api.miniproject.service.ItemService;
import com.api.miniproject.util.session.SessionUtil;
import com.api.miniproject.util.validation.LoginValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService service;
    
    @GetMapping("add")
    public String saveItemForm(Model model){
        model.addAttribute("item", new Item());
        return "item/addForm";
    }

    @PostMapping("add")
    public String saveItem(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        // TODO : 검증 별도 분리 필요, message 관련 고도화 필요
        boolean validation = itemValidation(item, bindingResult);
        if (!validation) return "item/addForm";

        Long userId = SessionUtil.getUserIdFromSession();
        item.setUserId(userId); // foreign key

        Item saveItem = service.saveItem(item);

        // 검증하기: 다시 할 수 있는 방법으로 redirectAttributes 여기다가 넣어서, 기존에 입력한 것들은 유지
        log.debug("저장된 item={}", saveItem);
        redirectAttributes.addAttribute("search-item", saveItem.getId());
        redirectAttributes.addAttribute("saveStatus", true);

        return "redirect:/item/item";
    }

    private boolean itemValidation(Item item, BindingResult bindingResult) {
        // TODO : 타 클래스에서도 사용 가능하도록 고도화 필요

        if(!StringUtils.hasText(item.getItemName())){
            bindingResult.rejectValue("itemName", "itemName", "상품의 이름을 입력하세요");
        }

        if(item.getPrice() == null || item.getPrice() <= 10){
            bindingResult.rejectValue("price", "price", "가격을 확인하세요");
        }

        if(item.getQuantity() == null || item.getQuantity() <= 0){
            bindingResult.rejectValue("quantity", "quantity", "수량을 확인하세요");
        }
        if(bindingResult.hasErrors()){
            log.info("item validation has Error = {}", item);
            return false;
        }
        return true;
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
    public String updateItem(@PathVariable Long itemId, @ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        boolean validation = itemValidation(item, bindingResult);
        if (!validation) return "item/editForm";

        service.updateItem(itemId, item);
        // 검증 필요
        log.info("업데이트된 item={}", item);
        redirectAttributes.addAttribute("search-item", item.getId());
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
        Item item3 = new Item("itemC", 100000, 50, "www.test3.com", 1L);
        service.saveItem(item1);
        service.saveItem(item2);
        service.saveItem(item3);
        log.debug("테스트 아이템 생성={}", item1.toString());
        log.debug("테스트 아이템 생성={}", item2.toString());
        log.debug("테스트 아이템 생성={}", item3.toString());
    }
}

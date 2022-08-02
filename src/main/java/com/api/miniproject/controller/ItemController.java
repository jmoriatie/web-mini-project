package com.api.miniproject.controller;

import com.api.miniproject.dto.Item;
import com.api.miniproject.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("add")
    public String saveItemForm(){
        return "item/addForm";
    }

    @PostMapping("add")
    public String saveItem(@ModelAttribute Item item, RedirectAttributes redirectAttributes) {
        Item saveItem = service.saveItem(item);

        // 검증하기: 다시 할 수 있는 방법으로 redirectAttributes 여기다가 넣어서, 기존에 입력한 것들은 유지
        log.debug("저장된 item={}", saveItem);
        redirectAttributes.addAttribute("search-item", saveItem.getId());
        redirectAttributes.addAttribute("saveStatus", true);

//        redirectAttributes.addAttribute("itemId", saveItem.getId()); // itemId 에 id 값을 저장해줌
//        return "redirect:/item/item?search-item={itemId}"; // redirectAttributes 의 itemId 나머지는 파라미터로 넘어감
        return "redirect:/item/item";
    }

    @GetMapping("items")
    public String findAll(Model model) {
        List<Item> items = service.findAll();

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
            findItem = findById(itemId);
        } catch (NumberFormatException e) {
            findItem = findByName(searchItem);
        }
        return findItem;
    }

    private Item findById(Long id) {
        Item findItem = service.findById(id);
        log.debug("findById={}", findItem);

        return findItem;
    }

    private Item findByName(String itemName) {
        Item findItem = service.findByName(itemName);
        log.debug("findByName={}", itemName);

        return findItem;
    }

    @GetMapping("{itemId}/edit")
    public String updateItemForm(@PathVariable Long itemId, Model model) {
        Item findItem = findById(itemId);
        model.addAttribute("item", findItem);

        return "item/editForm";
    }
    @PostMapping("{itemId}/edit")
    public String updateItem(@PathVariable Long itemId, @ModelAttribute Item item, RedirectAttributes redirectAttributes) {
        service.updateItem(itemId, item);
        // 검증 필요
        log.debug("업데이트된 item={}", item);
        redirectAttributes.addAttribute("search-item", item.getId());
        redirectAttributes.addAttribute("updateStatus", true);

        return "redirect:/item/item";
    }

    @GetMapping("{itemId}/delete")
    public String deleteItem(@PathVariable Long itemId) {
        service.deleteItem(itemId);
        return "redirect:/item/items";
    }

    @PostConstruct
    public void setTestItem() {
        Item item1 = new Item("itemA", 30000, 100, "www.test1.com");
        Item item2 = new Item("itemB", 50000, 75, "www.test2.com");
        Item item3 = new Item("itemC", 100000, 50, "www.test3.com");
        service.saveItem(item1);
        service.saveItem(item2);
        service.saveItem(item3);
        log.debug("아이템 생성={}", item1.toString());
        log.debug("아이템 생성={}", item2.toString());
        log.debug("아이템 생성={}", item3.toString());
    }

}

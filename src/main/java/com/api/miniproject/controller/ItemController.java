package com.api.miniproject.controller;

import com.api.miniproject.dto.Item;
import com.api.miniproject.service.ItemService;
import com.api.miniproject.util.StatusEnum;
import com.api.miniproject.util.StatusMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/add")
    public String saveItem(@ModelAttribute Item item, RedirectAttributes redirectAttributes) {
        Item saveItem = service.saveItem(item);
        redirectAttributes.addAttribute("item", saveItem);
        return "redirect:item/item?search-item=" + saveItem.getId();
    }

    @GetMapping("/items")
    public String findAll() {
        return "/";
    }

    /***
     * id, itemName 별도의 find 메서드 호출
     */
    @GetMapping("/item")
    public String selectFindItemMethod(@RequestParam(name = "search-item") String searchItem, Model model) {
        Item findItem;
        try {
            Long itemId = Long.parseLong(searchItem);
            log.debug("itemId={}", itemId);
            findItem = findById(itemId);
        } catch (NumberFormatException e) {
            findItem = findByName(searchItem);
        }


        if(findItem == null){
            return "redirect:/";
        }

        model.addAttribute("item", findItem);

        return "item/item";
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

    public String updateItem(Long id, Item item) {
        return "/";
    }

    public String deleteItem(Long id) {
        return "/";
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

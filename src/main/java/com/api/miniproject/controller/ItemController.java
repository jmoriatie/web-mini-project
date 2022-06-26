package com.api.miniproject.controller;

import com.api.miniproject.dto.Item;
import com.api.miniproject.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService service;

    @PostMapping
    String saveItem(@ModelAttribute Item item){
        return;
    }

    @GetMapping()
    String findAll(){
        return "/";
    }

    String findById(Long id){
        return "/";
    }

    String findByName(String itemName){
        return "/";
    }

    String updateItem(Long id, Item item){
        return "/";
    }

    String deleteItem(Long id){
        return "/";
    }

    @PostConstruct
    void setTestItem(){
        Item item1 = new Item("itemA", 30000, 100);
        Item item2 = new Item("itemB", 50000, 75);
        Item item3 = new Item("itemC", 100000, 50);
        saveItem(item1);
        saveItem(item2);
        saveItem(item3);
        log.debug("아이템 생성={}", item1);
        log.debug("아이템 생성={}", item2);
        log.debug("아이템 생성={}", item3);
    }

}

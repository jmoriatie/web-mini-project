package com.api.miniproject.service;

import com.api.miniproject.dto.Item;

import java.util.List;

public interface ItemService {

    Item saveItem(Item item);
    List<Item> findAll();
    Item findById(Long id);
    Item findByName(String itemName);
    void updateItem(Long id, Item item);
    void deleteItem(Long id);

}

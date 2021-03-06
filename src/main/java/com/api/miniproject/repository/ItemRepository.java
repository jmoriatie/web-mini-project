package com.api.miniproject.repository;

import com.api.miniproject.dto.Item;

import java.util.List;

public interface ItemRepository {

    Item saveItem(Item item);
    List<Item> findAll();
    Item findById(Long id);
    Item findByName(String itemName);
    void updateItem(Long id, Item item);
    void deleteItem(Long id);
}

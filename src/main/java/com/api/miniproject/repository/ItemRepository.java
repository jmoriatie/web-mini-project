package com.api.miniproject.repository;

import com.api.miniproject.domain.Item;

import java.util.List;

public interface ItemRepository {

    Item saveItem(Item item);
    List<Item> findAll();
    List<Item> findUserItems(Long userId);
    Item findById(Long id);
    Item findByName(String itemName);
    void updateItem(Long id, Item item);
    void deleteItem(Long id);
}

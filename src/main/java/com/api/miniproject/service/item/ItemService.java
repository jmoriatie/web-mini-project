package com.api.miniproject.service.item;

import com.api.miniproject.domain.Item;


import java.util.List;
import java.util.Optional;

public interface ItemService {

    Item saveItem(Item item);
    List<Item> findAll();
    List<Item> findUserItems(String userId);
    Item findById(Long id);
    Item findByName(String itemName);
    void updateItem(Long id, Item item);
    void deleteItem(Long id);
}

package com.api.miniproject.service.item;

import com.api.miniproject.domain.Item;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ItemService {

    Item saveItem(Item item);
    List<Item> findAll();
    List<Item> findUserItems(Long userId);
    Item findById(Long id);
    Item findByName(String itemName);
    void updateItem(Long id, Item item);
    void deleteItem(Long id);
}

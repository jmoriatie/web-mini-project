package com.api.miniproject.repository;

import com.api.miniproject.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ItemRepositoryJPA{
//    List<Item> findUserItems(Long userId);
    Item findByName(String itemName);
    Item saveItem(Item item);
    void updateItem(Long id, Item item);
}

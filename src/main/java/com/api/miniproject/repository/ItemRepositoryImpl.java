package com.api.miniproject.repository;

import com.api.miniproject.domain.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
//@Repository
public class ItemRepositoryImpl{

    private static Long sequence = 0L;
    private static Map<Long, Item> storage = new ConcurrentHashMap<>(); // 동시접속 문제 해결 hashMap(local이라 딱히 동시접속 문제는 없지만)

    public Item saveItem(Item item) {
//        item.setId(++sequence);
        log.info("saveItem={}", item.toString());
        storage.put(item.getId(), item);
        return Item.builder()
                .itemName(item.getItemName())
                .userId(item.getUserId())
                .price(item.getPrice())
                .quantity(item.getQuantity())
                .buyUrl(item.getBuyUrl())
                .build();
    }

    public List<Item> findAll() {
        return new ArrayList<>( storage.values() );
    }

    public List<Item> findUserItems(Long userId) {
        List<Item> items = new ArrayList<>( storage.values() );
        List<Item> findUserItems = new ArrayList<>();
        for (Item item : items) {
            if(item.getUserId().equals(userId)){
                findUserItems.add(item);
            }
        }
        return findUserItems;
    }

    public Item findById(Long id) {
        Item item = null;
        if(storage.containsKey(id)){
            item = storage.get(id);
        }
        return item;
    }

    public Item findByName(String itemName) {
        List<Item> itemList = findAll();

        return itemList.stream()
                .filter(item -> (item.getItemName()).equals(itemName))
                .findAny()
                .orElse(null);
    }

    public void updateItem(Long id, Item item) {
        storage.get(id).update(
                Item.builder()
                        .itemName(item.getItemName())
                        .userId(item.getUserId())
                        .price(item.getPrice())
                        .quantity(item.getQuantity())
                        .buyUrl(item.getBuyUrl())
                        .build()
        );
    }

    public void deleteItem(Long id) {
        storage.remove(id);
    }

}

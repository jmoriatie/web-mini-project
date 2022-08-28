package com.api.miniproject.serviceTest;

import com.api.miniproject.domain.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Repository
public class ItemRepositoryImpl implements ItemRepository{

    private static Long sequence = 0L;
    private static Map<Long, Item> storage = new ConcurrentHashMap<>(); // 동시접속 문제 해결 hashMap(local이라 딱히 동시접속 문제는 없지만)

    @Override
    public Item saveItem(Item item) {
        item.setId(++sequence);
        log.info("saveItem={}", item.toString());
        storage.put(item.getId(), item);
        return item;
    }

    @Override
    public List<Item> findAll() {
        return new ArrayList<>( storage.values() );
    }

    @Override
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


    @Override
    public Item findById(Long id) {
        Item item = null;
        if(storage.containsKey(id)){
            item = storage.get(id);
        }
        return item;
    }

    @Override
    public Item findByName(String itemName) {
        List<Item> itemList = findAll();

        return itemList.stream()
                .filter(item -> (item.getItemName()).equals(itemName))
                .findAny()
                .orElse(null);
    }

    @Override
    public void updateItem(Long id, Item item) {
        storage.get(id).update(item);
    }

    @Override
    public void deleteItem(Long id) {
        storage.remove(id);
    }

}

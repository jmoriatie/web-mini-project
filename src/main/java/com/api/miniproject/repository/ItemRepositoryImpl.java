package com.api.miniproject.repository;

import com.api.miniproject.dto.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Repository
public class ItemRepositoryImpl implements ItemRepository{

    private static Long sequence = 0L;
    private static Map<Long, Item> storage = new ConcurrentHashMap<>(); // local이라 딱히 동시접속 문제는 없지만 하하

    @Override
    public Item saveItem(Item item) {
        item.setId(sequence++);
        storage.put(item.getId(), item);
        return item;
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>( storage.values() );
        return items;
    }

    @Override
    public Item findById(Long id) {
        Item item = null;
        if(storage.containsKey(id)){
            item = storage.get(id);
        }
        return item;
    }

    // 아이템 이름 중복 문제 해결 필요
    @Override
    public Item findByName(String itemName) {
        List<Item> itemList = findAll();

        return itemList.stream().
                filter(item -> item.getItemName().equals(itemName))
                .findAny()
                .orElse(null);
    }

    @Override
    public void updateItem(Long id, Item item) {
        storage.get(id).update(item.getItemName(), item.getPrice(), item.getAmount());
    }

    @Override
    public void deleteItem(Long id) {
        storage.remove(id);
    }

}

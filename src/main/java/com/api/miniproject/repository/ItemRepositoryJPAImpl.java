package com.api.miniproject.repository;

import com.api.miniproject.domain.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class ItemRepositoryJPAImpl implements ItemRepositoryJPA {

    private static Long sequence = 0L;
    private final ItemRepository repo;

    @Autowired
    @Lazy
    public ItemRepositoryJPAImpl(ItemRepository itemRepository) {
        this.repo = itemRepository;
    }

    public Item saveItem(Item item) {
        item.setId(++sequence);
        log.info("saveItem={}", item);
        return repo.save(item);
    }

    public Item findByName(String itemName) {
        List<Item> itemList = repo.findAll();

        return itemList.stream()
                .filter(item -> (item.getItemName()).equals(itemName))
                .findAny()
                .orElse(null);
    }

    @Transactional
    public void updateItem(Long id, Item item) {
        repo.findById(id).ifPresent(realItem -> realItem.update(item));
    }
}

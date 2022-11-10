package com.api.miniproject.repository.jpa;

import com.api.miniproject.domain.Item;
import com.api.miniproject.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Repository
public class ItemRepositoryJPAImpl implements ItemRepositoryJPA {

    private final ItemRepository repo;

    @Autowired
    @Lazy
    public ItemRepositoryJPAImpl(ItemRepository itemRepository) {
        this.repo = itemRepository;
    }

    public Item saveItem(Item item) {
        log.info("saveItem={}", item);
        return repo.save(
                Item.builder()
                        .itemName(item.getItemName())
                        .price(item.getPrice())
                        .quantity(item.getQuantity())
                        .buyUrl(item.getBuyUrl())
                        .userId(item.getAccountId())
                        .build()
                );
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
        repo.findById(id).ifPresent(realItem -> realItem.update(
                Item.builder()
                        .itemName(item.getItemName())
                        .userId(item.getAccountId())
                        .price(item.getPrice())
                        .quantity(item.getQuantity())
                        .buyUrl(item.getBuyUrl())
                        .build()
        ));
    }
}

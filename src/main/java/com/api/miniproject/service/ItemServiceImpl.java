package com.api.miniproject.service;

import com.api.miniproject.domain.Item;
import com.api.miniproject.serviceTest.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService{


    private final ItemRepository repo;

    @Autowired
    public ItemServiceImpl(ItemRepository repo) {
        this.repo = repo;
    }

    @Override
    public Item saveItem(Item item) {
        return repo.saveItem(item);
    }

    @Override
    public List<Item> findAll() {
        return repo.findAll();
    }

    @Override
    public List<Item> findUserItems(Long userId) {
        return repo.findUserItems(userId);
    }

    @Override
    public Item findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Item findByName(String itemName) {
        return repo.findByName(itemName);
    }

    @Override
    public void updateItem(Long id, Item item) {
        repo.updateItem(id, item);
    }

    @Override
    public void deleteItem(Long id) {
        repo.deleteItem(id);
    }
}

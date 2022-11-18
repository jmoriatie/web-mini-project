package com.api.miniproject.service.item;

import com.api.miniproject.domain.Item;
import com.api.miniproject.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService{

    private final ItemRepository repo;

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
    public List<Item> findUserItems(String accountId) {
        return repo.findUserItems(accountId);
    }

    @Override
    public Item findById(Long id) {
        return repo.findById(id).orElse(null);
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
        repo.deleteById(id);
    }
}

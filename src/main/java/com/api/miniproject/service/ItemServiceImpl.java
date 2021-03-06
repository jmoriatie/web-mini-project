package com.api.miniproject.service;

import com.api.miniproject.dto.Item;
import com.api.miniproject.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private ItemRepository repo;

    @Override
    public Item saveItem(Item item) {
        return repo.saveItem(item);
    }

    @Override
    public List<Item> findAll() {
        return repo.findAll();
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

    @PostConstruct
    private void successMessage(){
        log.debug("ServiceImpl 생성={}", ItemServiceImpl.class);
    }


}

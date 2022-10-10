package com.api.miniproject.repository;

import com.api.miniproject.domain.Item;
import com.api.miniproject.service.item.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SpringItemServiceTest {

    @Autowired
    private ItemService service;
    @Autowired
    private ItemRepository repository;

    @Test
    void findAllJPATest() {
        List<Item> all = repository.findAll();
        System.out.println("all = " + all);

        List<Item> list = service.findAll();
        System.out.println("list = " + list);

        Item findById = service.findById(1L);
        System.out.println("findById = " + findById);


        List<Item> userItemsTest = repository.findUserItems(1L);
        System.out.println("userItemsTest = " + userItemsTest);

        // test case userId 1L 은 60개 만듦
        assertThat(userItemsTest.size()).isEqualTo(60);
    }

}

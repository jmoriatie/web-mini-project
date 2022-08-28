package com.api.miniproject.serviceTest;

import com.api.miniproject.domain.Item;
import com.api.miniproject.domain.User;
import com.api.miniproject.service.ItemService;
import com.api.miniproject.service.ItemServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ItemServiceTest {

    ItemService service = new ItemServiceImpl(new ItemRepositoryImpl());

    @Test
    void saveItemTest() {
        Item item = new Item("test",9999,100,"www.xxx.com", 8L);

        Item savedItem = service.saveItem(item);

        assertThat(savedItem.getItemName()).isEqualTo("test");
    }

    @Test
    void findAllTest() {
        Item itemA = new Item("testItemA",9999,100,"www.xxx.com", 10L);
        Item itemB = new Item("testItemB",9999,100,"www.yyy.com", 20L);

        service.saveItem(itemA);
        service.saveItem(itemB);

        List<Item> allItem = service.findAll();

        assertThat(allItem.size()).isEqualTo(2);
        assertThat(allItem.size()).isNotEqualTo(3);
    }

    @Test
    void findUserItemsTest() {
        Item itemA = new Item("testItemA",9999,100,"www.xxx.com", 1L);
        Item itemB = new Item("testItemB",3333,33,"www.yyy.com", 2L);

        service.saveItem(itemA);
        service.saveItem(itemB);

        User userA = new User("userA", "xxx", "nameA");
        userA.setId(1L); // 로직에서 자동 설정되는 값이기에 수동 설정

        List<Item> userItems = service.findUserItems(userA.getId());

        assertThat(userItems).contains(itemA);
        assertThat(userItems).doesNotContain(itemB);
    }

    @Test
    void findByIdTest() {
    }

    @Test
    void findByNameTest() {
        Item itemA = new Item("testItemA",9999,100,"www.xxx.com", 1L);

        service.saveItem(itemA);

        Item findItem = service.findByName("testItemA");
        assertThat(findItem).isEqualTo(itemA);
    }

    @Test
    void updateItemTest() {
        Item itemA = new Item("testItemA",9999,100,"www.xxx.com", 1L);

        service.saveItem(itemA);

        itemA.setItemName("UpdatedItemA");
        itemA.setPrice(500);
        itemA.setQuantity(10);

        itemA.setId(1L); // 로직에서 자동 설정되는 값이기에 수동 설정

        service.updateItem(itemA.getId(), itemA);

        assertThat(itemA.getItemName()).isEqualTo("UpdatedItemA");
        assertThat(itemA.getPrice()).isEqualTo(500);
        assertThat(itemA.getQuantity()).isEqualTo(10);
        assertThat(itemA.getItemName()).isNotEqualTo("testItemA");
    }

    @Test
    void deleteItemTest() {
        Item itemA = new Item("testItemA",9999,100,"www.xxx.com", 1L);
        Item itemB = new Item("testItemB",3333,33,"www.yyy.com", 2L);

        service.saveItem(itemA);
        service.saveItem(itemB);

        service.deleteItem(itemA.getId());

        List<Item> items = service.findAll();

        assertThat(items.size()).isEqualTo(1);
        assertThat(items).doesNotContain(itemA);
    }
}
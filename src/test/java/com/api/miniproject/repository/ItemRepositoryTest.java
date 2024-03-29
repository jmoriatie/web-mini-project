package com.api.miniproject.repository;

import com.api.miniproject.domain.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@DisplayName("Item CRUD 테스트")
@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:application-test.properties")
class ItemRepositoryTest {

    @Autowired
    private ItemRepository repository;

    @Test
    @DisplayName("saveItemTest 테스트")
    void saveItemTest() {
        Item item = new Item("test", 9999, 100, "www.xxx.com", "tester");
        Item savedItem = repository.saveItem(item);
        assertThat(savedItem.getItemName()).isEqualTo("test");
    }

    @Test
    @DisplayName("findUserItemsTest 테스트")
    void findUserItemsTest() {
        Item itemA = Item.builder()
                .itemName("testItemA")
                .price(9999)
                .quantity(100)
                .buyUrl("www.xxx.com")
                .accountId("tester").build();

        Item testItem = repository.saveItem(itemA);

        List<Item> userItems = repository.findUserItems("tester");

        assertThat(userItems).contains(testItem);
    }

    @Test
    @DisplayName("findByNameTest 테스트")
    void findByNameTest() {
        Item itemA = Item.builder()
                .itemName("testItemA")
                .price(9999)
                .quantity(100)
                .buyUrl("www.xxx.com")
                .accountId("tester").build();

        Item savedItem = repository.saveItem(itemA);

        Item findItem = repository.findByName("testItemA");
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    @Transactional
    @DisplayName("updateItemTest 테스트")
    void updateItemTest() {
        Item itemA = Item.builder()
                .itemName("testItemA")
                .price(9999)
                .quantity(100)
                .buyUrl("www.xxx.com")
                .accountId("tester").build();

        Item item = repository.saveItem(itemA);

        repository.updateItem(item.getId(), Item.builder()
                .itemName("UpdatedItemA")
                .price(500)
                .quantity(10).build());

        assertThat(item.getItemName()).isEqualTo("UpdatedItemA");
        assertThat(item.getPrice()).isEqualTo(500);
        assertThat(item.getQuantity()).isEqualTo(10);
        assertThat(item.getItemName()).isNotEqualTo("testItemA");
    }

    @Test
    @DisplayName("deleteItem 테스트")
    void deleteItemTest() {
        Item itemA = Item.builder()
                .itemName("testItemA")
                .price(9999)
                .quantity(100)
                .buyUrl("www.xxx.com")
                .accountId("tester").build();

        Item item = repository.saveItem(itemA);

        repository.delete(item);

        List<Item> items = repository.findAll();

        assertThat(items).doesNotContain(itemA);
    }

    @Test
    @DisplayName("findAll JPA 테스트")
    void findAllJPATest() {
        List<Item> list = repository.findAll();
        assertThat(list.size()).isNotNull();
    }
}
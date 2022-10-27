package com.api.miniproject.repository;

import com.api.miniproject.domain.Item;
import com.api.miniproject.domain.User;
import com.api.miniproject.service.item.ItemServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

//    private ItemService service = new ItemServiceImpl();

    @Mock
    private ItemRepository repo;

    @InjectMocks // jpa 테스트 하기 위해 mock 생성
    private ItemServiceImpl service;

    @Test
    void saveItemTest() {
        Item item = new Item("test", 9999, 100, "www.xxx.com", 8L);
        Item savedItem = service.saveItem(item);
        assertThat(savedItem.getItemName()).isEqualTo("test");
    }

    @Test
    void findAllTest() {
        Item itemA = new Item("testItemA", 9999, 100, "www.xxx.com", 10L);
        Item itemB = new Item("testItemB", 9999, 100, "www.yyy.com", 20L);

        service.saveItem(itemA);
        service.saveItem(itemB);

        List<Item> allItem = service.findAll();

        assertThat(allItem.size()).isEqualTo(2);
        assertThat(allItem.size()).isNotEqualTo(3);
    }

    @Test
    void findUserItemsTest() {
        Item itemA = new Item("testItemA", 9999, 100, "www.xxx.com", 1L);
        Item itemB = new Item("testItemB", 3333, 33, "www.yyy.com", 2L);

        service.saveItem(itemA);
        service.saveItem(itemB);

        User userA = new User("userA", "xxx", "nameA");
//        userA.setId(1L); // 로직에서 자동 설정되는 값이기에 수동 설정 => JPA 변경

        List<Item> userItems = service.findUserItems(userA.getId());

        assertThat(userItems).contains(itemA);
        assertThat(userItems).doesNotContain(itemB);
    }

    @Test
    void findByIdTest() {
    }

    @Test
    void findByNameTest() {
        Item itemA = new Item("testItemA", 9999, 100, "www.xxx.com", 1L);

        service.saveItem(itemA);

        Item findItem = service.findByName("testItemA");
        assertThat(findItem).isEqualTo(itemA);
    }

    @Test
    void updateItemTest() {
        Item itemA = new Item("testItemA", 9999, 100, "www.xxx.com", 1L);

        service.saveItem(itemA);

        Item.builder()
                .itemName("UpdatedItemA")
                .price(500)
                .quantity(10).build();


        service.updateItem(itemA.getId(), itemA);

        assertThat(itemA.getItemName()).isEqualTo("UpdatedItemA");
        assertThat(itemA.getPrice()).isEqualTo(500);
        assertThat(itemA.getQuantity()).isEqualTo(10);
        assertThat(itemA.getItemName()).isNotEqualTo("testItemA");
    }

    @Test
    void deleteItemTest() {
        Item itemA = new Item("testItemA", 9999, 100, "www.xxx.com", 1L);
        Item itemB = new Item("testItemB", 3333, 33, "www.yyy.com", 2L);

        service.saveItem(itemA);
        service.saveItem(itemB);

        service.deleteItem(itemA.getId());

        List<Item> items = service.findAll();

        assertThat(items.size()).isEqualTo(1);
        assertThat(items).doesNotContain(itemA);
    }

    @Test
    void findAllJPATest() {
            Item itemA = new Item("testItemA", 9999, 100, "www.xxx.com", 1L);
        Item itemB = new Item("testItemB", 3333, 33, "www.yyy.com", 2L);

//        service.saveItem(itemA);
//        service.saveItem(itemB);
//
//        BDDMockito.given(repo.saveItem(itemA)).willReturn(itemA);
//        BDDMockito.given(repo.saveItem(itemB)).willReturn(itemB);

        List<Item> all = repo.findAll();
        System.out.println("all = " + all);

        Item findById = service.findById(1L);
        System.out.println("findById = " + findById);

        List<Item> list = service.findAll();
        System.out.println("list = " + list);
        assertThat(list.size()).isNotNull();
    }
}
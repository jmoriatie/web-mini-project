package com.api.miniproject.util;

import com.api.miniproject.domain.Item;
import com.api.miniproject.domain.User;
import com.api.miniproject.service.item.ItemService;
import com.api.miniproject.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestObjectInit {

    private final UserService userService;
    private final ItemService itemService;

    @PostConstruct
    void testObjectInit(){
        User user1 = new User("test", "test", "NaTest");
        User user2 = new User("a", "a", "김에이");

        userService.saveUser(user1);
        userService.saveUser(user2);

        Item item1 = new Item("itemA", 30000, 100, "www.test1.com", 1L);
        Item item2 = new Item("itemB", 50000, 75, "www.test2.com", 1L);
        Item item3 = new Item("itemC", 100000, 50, "www.test3.com", 2L);

        itemService.saveItem(item1);
        itemService.saveItem(item2);
        itemService.saveItem(item3);
    }
}

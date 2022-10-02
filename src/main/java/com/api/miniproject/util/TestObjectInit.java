package com.api.miniproject.util;

import com.api.miniproject.domain.Item;
import com.api.miniproject.domain.User;
import com.api.miniproject.service.item.ItemService;
import com.api.miniproject.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class TestObjectInit {

    private final UserService userService;
    private final ItemService itemService;



    @PostConstruct
    void testObjectInit(){
        Random random = new Random();

        User user1 = new User("test", "test", "나테스트");
        User user2 = new User("a", "a", "김에이");

        userService.saveUser(user1);
        userService.saveUser(user2);
//
//        Item item1 = new Item("itemA", 30000, 100, "www.test1.com", 1L);
//        Item item2 = new Item("itemB", 50000, 75, "www.test2.com", 1L);
//        Item item3 = new Item("itemC", 30000, 231, "www.test3.com", 1L);
//        Item item4 = new Item("itemD", 3000, 222, "www.test4.com", 1L);
//        Item item5 = new Item("itemE", 60000, 100, "www.test5.com", 1L);
//        Item item6 = new Item("itemF", 5000, 888, "www.test6.com", 1L);
//        Item item7 = new Item("itemG", 300, 100, "www.test7.com", 1L);
//        Item item8 = new Item("itemH", 150000, 666, "www.test8.com", 1L);
//        Item item9 = new Item("itemI", 7000, 100, "www.test9.com", 1L);
//        Item item10 = new Item("itemJ", 32000, 102, "www.test10.com", 1L);
//        Item item11 = new Item("itemK", 90000, 54, "www.test11.com", 1L);
//        Item item12 = new Item("itemL", 100000, 32, "www.test12.com", 1L);
//
//        Item item13 = new Item("itemC", 100000, 50, "www.test3.com", 2L);
//        Item item14 = new Item("itemD", 1000, 80, "www.test4.com", 2L);

        for(int i=1; i<=15; i++){
            int price = random.nextInt(100000) + 10;
            int quantity = random.nextInt(1500) + 1;
            Item item;
            if(i <= 12){
                item = new Item("item"+i, price, quantity, "www.test"+i+".com", 1L);
            }
            else{
                item = new Item("item"+i, price, quantity, "www.test"+i+".com", 2L);
            }
            itemService.saveItem(item);
        }
    }
}

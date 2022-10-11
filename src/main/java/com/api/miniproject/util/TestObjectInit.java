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

    private final String[] ITEM_TYPE = {"프린팅", "해골", "다이나믹", "골져스", "프리티","디스트로이", "빈티지", "다크", "오버핏", "트레이닝"};
    private final String[] ITEM_CATEGORY ={"티셔츠", "팬츠", "조끼", "후드", "와이트팬츠", "반바지", "셔츠", "카라티", "청바지", "긴팔티셔츠"};

//    @PostConstruct
    void testUserInit(){
        Random random = new Random();

        User user1 = new User("test", "test", "나테스트");
        User user2 = new User("a", "a", "김에이");

        userService.saveUser(user1);
        userService.saveUser(user2);
    }

//    @PostConstruct
    void testItemInit(){
        Random random = new Random();

        for(int i=1; i<=70; i++){
            int price = random.nextInt(100000) + 10;
            int quantity = random.nextInt(1500) + 1;
            String type = ITEM_TYPE[random.nextInt(ITEM_TYPE.length)];
            String category = ITEM_CATEGORY[random.nextInt(ITEM_TYPE.length)];

            Item item;
            if(i <= 60){
                item = new Item(type+"-"+category, price, quantity, "www.test"+i+".com", 1L);
            }
            else{
                item = new Item(type+"-"+category, price, quantity, "www.test"+i+".com", 2L);
            }
            itemService.saveItem(item);
        }
    }
}

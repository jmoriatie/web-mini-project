package com.api.miniproject.service;

import com.api.miniproject.domain.User;
import com.api.miniproject.service.user.UserService;
import com.api.miniproject.service.user.UserServiceImpl;
import com.api.miniproject.serviceTest.UserRepositoryImpl;
import org.junit.jupiter.api.Test;

class UserServiceTest {

    UserService service = new UserServiceImpl(new UserRepositoryImpl());

    @Test
    void loginTest() {

        User user = new User("test", "test", "ttt");
        user.setId(1L);


//        User sessionUser = (User)SessionUtil.getSession().getAttribute(SessionConst.LOGIN_USER);

//        assertThat(sessionUser.getUserId()).isEqualTo("test");
    }

    @Test
    void logoutTest() {
    }
}
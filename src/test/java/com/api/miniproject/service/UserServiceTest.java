package com.api.miniproject.service;

import com.api.miniproject.domain.User;
import com.api.miniproject.serviceTest.UserRepository;
import com.api.miniproject.serviceTest.UserRepositoryImpl;
import com.api.miniproject.util.session.SessionConst;
import com.api.miniproject.util.session.SessionUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.http.client.MockClientHttpRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

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
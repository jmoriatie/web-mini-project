package com.api.miniproject.service;

import com.api.miniproject.domain.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


public interface
UserService {

    User login(String userId, String userPw);
    User saveUser(User user);
    void logout();
}

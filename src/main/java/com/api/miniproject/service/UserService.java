package com.api.miniproject.service;

import com.api.miniproject.domain.User;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface UserService {

    Optional<User> login(String userId, String userPw);
}

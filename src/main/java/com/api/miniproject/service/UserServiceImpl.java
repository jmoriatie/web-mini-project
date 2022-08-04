package com.api.miniproject.service;

import com.api.miniproject.domain.User;
import com.api.miniproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository repo;

    @Override
    public Optional<User> login(String userId, String userPw){
        // Optional 로 User 또는 null이 넘어옴
        return repo.findByUserId(userId)
                .filter(u -> u.getUserPw().equals(userPw));
    }
}

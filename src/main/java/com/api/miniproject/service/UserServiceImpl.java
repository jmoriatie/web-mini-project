package com.api.miniproject.service;

import com.api.miniproject.domain.User;
import com.api.miniproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository repo;

    @Autowired
    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public User login(String userId, String userPw){
        return repo.findByUserId(userId)
                .filter(u -> u.getUserPw().equals(userPw)).orElseGet(() -> null);
    }

    @Override
    public User saveUser(User user) {
        return repo.saveUser(user);
    }
}

package com.api.miniproject.service.user;

import com.api.miniproject.domain.User;
import com.api.miniproject.serviceTest.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    @Autowired
    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public User saveUser(User user) {
        return repo.saveUser(user);
    }
}



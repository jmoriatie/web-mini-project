package com.api.miniproject.service.user;

import com.api.miniproject.domain.User;
import com.api.miniproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    @Override
    public User saveUser(User user) {
        return repo.saveUser(user);
    }

    public User findByUserId(String userId){
        return repo.findByUserId(userId).orElse(null);
    }
}



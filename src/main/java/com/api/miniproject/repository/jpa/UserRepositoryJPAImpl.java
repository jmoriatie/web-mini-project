package com.api.miniproject.repository.jpa;

import com.api.miniproject.domain.User;
import com.api.miniproject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
public class UserRepositoryJPAImpl implements UserRepositoryJPA {

    private final UserRepository userRepository;

    @Autowired
    @Lazy
    public UserRepositoryJPAImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user){
        User savedUser = userRepository.save(user);
        log.info("savedUser={}", savedUser);
        return savedUser;
    }

    public Optional<User> findByUserId(String userId){
        return userRepository.findAll().stream()
                .filter(u -> u.getUserId().equals(userId)).findFirst();
    }
}

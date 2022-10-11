package com.api.miniproject.repository;

import com.api.miniproject.domain.User;

import java.util.Optional;

public interface UserRepositoryJPA {
    User saveUser(User user);
    Optional<User> findByUserId(String userId);
}

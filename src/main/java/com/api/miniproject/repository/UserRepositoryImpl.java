package com.api.miniproject.repository;

import com.api.miniproject.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
//@Repository
public class UserRepositoryImpl {

    private static Long sequence = 0L;
    private final static Map<Long, User> storage = new ConcurrentHashMap<>();

    public User saveUser(User user) {
//        user.setId(++sequence);
        storage.put(user.getId(), user);
        log.info("saveUser={}", user.toString());
        return user;
    }

    public List<User> findAll() {
        return new ArrayList<User>(storage.values());
    }

    public User findById(Long id) {
        return storage.get(id);
    }

    public Optional<User> findByUserId(String userId) {
        return findAll().stream()
                .filter(u -> u.getUserId().equals(userId)).findFirst();
    }

    public void updateUser(User user) {

    }

    public void deleteUser(Long id) {

    }
}

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
@Repository
public class UserRepositoryImpl implements UserRepository{

    private static Long sequence = 0L;
    private final static Map<Long, User> storage = new ConcurrentHashMap<>();

    @Override
    public User saveUser(User user) {
        user.setId(++sequence);
        storage.put(user.getId(), user);
        log.info("saveUser={}", user.toString());
        return user;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<User>(storage.values());
    }

    @Override
    public User findById(Long id) {
        return storage.get(id);
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        Optional<User> user = findAll().stream()
                .filter(u -> u.getUserId().equals(userId)).findFirst();
        log.info("is there user? ={}", user);
        return user;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(Long id) {

    }
}

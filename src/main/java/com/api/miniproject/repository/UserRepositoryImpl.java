package com.api.miniproject.repository;

import com.api.miniproject.dto.Item;
import com.api.miniproject.dto.User;
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
    private static Map<Long, User> storage = new ConcurrentHashMap<>();

    @Override
    public List<User> findAll() {
        return new ArrayList<User>(storage.values());
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByUserInfo(String userId, String userPw) {
        Optional<User> findUser = findAll().stream()
                .filter(u -> u.getUserId().equals(userId)).findFirst();
//        findUser = Optional.ofNullable(findUser).orElseGet()

        return Optional.empty();
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(Long id) {

    }
}

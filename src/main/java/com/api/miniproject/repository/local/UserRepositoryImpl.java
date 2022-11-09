package com.api.miniproject.repository.local;

import com.api.miniproject.domain.Account;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
//@Repository
public class UserRepositoryImpl {

    private static Long sequence = 0L;
    private final static Map<Long, Account> storage = new ConcurrentHashMap<>();

    public Account saveAccount(Account account) {
//        user.setId(++sequence);
        storage.put(account.getId(), account);
        log.info("saveUser={}", account.toString());
        return account;
    }

    public List<Account> findAll() {
        return new ArrayList<Account>(storage.values());
    }

    public Account findById(Long id) {
        return storage.get(id);
    }

    public Optional<Account> findByAccountId(String userId) {
        return findAll().stream()
                .filter(u -> u.getAccountId().equals(userId)).findFirst();
    }

    public void updateAccount(Account user) {

    }

    public void deleteAccount(Long id) {

    }
}

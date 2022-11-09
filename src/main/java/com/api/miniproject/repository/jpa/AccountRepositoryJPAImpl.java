package com.api.miniproject.repository.jpa;

import com.api.miniproject.domain.Account;
import com.api.miniproject.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
public class AccountRepositoryJPAImpl implements AccountRepositoryJPA {

    private final AccountRepository accountRepository;

    @Autowired
    @Lazy
    public AccountRepositoryJPAImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account saveAccount(Account account){
        Account savedAccount = accountRepository.save(account);
        log.info("savedUser={}", savedAccount);
        return savedAccount;
    }

    public Optional<Account> findByAccountId(String accountId){
        return accountRepository.findAll().stream()
                .filter(u -> u.getAccountId().equals(accountId)).findFirst();
    }
}

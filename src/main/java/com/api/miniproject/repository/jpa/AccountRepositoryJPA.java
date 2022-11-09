package com.api.miniproject.repository.jpa;

import com.api.miniproject.domain.Account;

import java.util.Optional;

public interface AccountRepositoryJPA {
    Account saveAccount(Account account);
    Optional<Account> findByAccountId(String accountId);
}

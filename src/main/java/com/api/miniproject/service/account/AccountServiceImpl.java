package com.api.miniproject.service.account;

import com.api.miniproject.domain.Account;
import com.api.miniproject.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repo;

    @Override
    public Account saveAccount(Account account) {
        return repo.saveAccount(account);
    }

    public Account findByAccountId(String accountId){
        return repo.findByAccountId(accountId).orElse(null);
    }
}



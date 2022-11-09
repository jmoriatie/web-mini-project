package com.api.miniproject.service.account;

import com.api.miniproject.domain.Account;

public interface AccountService {
    Account saveAccount(Account account);
    Account findByAccountId(String accountId);
}

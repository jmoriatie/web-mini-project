package com.api.miniproject.service.account;

import com.api.miniproject.domain.Account;
import com.api.miniproject.dto.account.JoinDto;

import java.util.List;

public interface AccountService {
    Account saveAccount(Account account);
    Account findByAccountId(String accountId);

    JoinDto join(JoinDto joinDto);
    JoinDto createUserForAdmin(JoinDto joinDto);
}

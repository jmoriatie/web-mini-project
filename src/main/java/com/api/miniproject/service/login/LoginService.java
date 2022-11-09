package com.api.miniproject.service.login;

import com.api.miniproject.domain.Account;

public interface LoginService {
    Account findByAccountId(String userId, String userPw);
}

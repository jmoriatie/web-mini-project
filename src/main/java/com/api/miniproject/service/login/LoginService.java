package com.api.miniproject.service.login;

import com.api.miniproject.domain.User;

public interface LoginService {
    User findByUserId(String userId, String userPw);
    void logout();
}

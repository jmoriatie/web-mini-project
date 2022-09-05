package com.api.miniproject.service;

import com.api.miniproject.domain.User;

public interface LoginService {
    User login(String userId, String userPw);
    void logout();
}

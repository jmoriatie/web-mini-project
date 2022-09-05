package com.api.miniproject.service;

import com.api.miniproject.domain.User;
import com.api.miniproject.serviceTest.UserRepository;
import com.api.miniproject.util.session.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 로그아웃, 로그인 되어있을 때 검증 -> 로그인 검증하는 그거 LoginValidationUtil 로 만들기, 유저 삭제
@Service
public class LoginServiceImpl implements LoginService{

    private final UserRepository repo;

    @Autowired
    public LoginServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public User login(String userId, String userPw){
        return repo.findByUserId(userId)
                .filter(u -> u.getUserPw().equals(userPw)).orElseGet(() -> null);
    }

    @Override
    public void logout() {
        SessionUtil.sessionInvalidate();
    }

}

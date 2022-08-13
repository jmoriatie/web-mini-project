package com.api.miniproject.service;

import com.api.miniproject.domain.User;
import com.api.miniproject.repository.UserRepository;
import com.api.miniproject.util.session.SessionUtil;
import com.api.miniproject.util.validation.LoginValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

// 로그아웃, 로그인 되어있을 때 검증 -> 로그인 검증하는 그거 LoginValidationUtil 로 만들기, 유저 삭제

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository repo;

    @Autowired
    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public User login(String userId, String userPw){
        return repo.findByUserId(userId)
                .filter(u -> u.getUserPw().equals(userPw)).orElseGet(() -> null);
    }

    @Override
    public User saveUser(User user) {
        return repo.saveUser(user);
    }

    @Override
    public void logout() {
        SessionUtil.sessionInvalidate();
    }
}

package com.api.miniproject.service.login;

import com.api.miniproject.domain.Account;
import com.api.miniproject.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// 로그아웃, 로그인 되어있을 때 검증 -> 로그인 검증하는 그거 LoginValidationUtil 로 만들기, 유저 삭제
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{

    private final AccountRepository repo;

    @Override
    public Account findByAccountId(String userId, String userPw){
        return repo.findByAccountId(userId)
                .filter(u -> u.getAccountId().equals(userPw)).orElseGet(() -> null);
    }
}

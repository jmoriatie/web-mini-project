package com.api.miniproject.service.account;

import com.api.miniproject.domain.Account;
import com.api.miniproject.domain.Authority;
import com.api.miniproject.dto.account.JoinDto;
import com.api.miniproject.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Account saveAccount(Account account) {
        return repo.saveAccount(account);
    }

    public Account findByAccountId(String accountId){
        return repo.findByAccountId(accountId).orElse(null);
    }

    @Transactional
    public JoinDto join(JoinDto joinDto){
        if(repo.findOneWithAuthoritiesByAccountId(joinDto.getAccountId()).isPresent()){
            throw new RuntimeException("이미 가입된 유저입니다.");
        }

        Authority role = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Account account = Account.builder()
                .accountId(joinDto.getAccountId())
                .accountPw(passwordEncoder.encode(joinDto.getAccountPw()))
                .accountName(joinDto.getAccountName())
                .authorities(Collections.singleton(role))
                .build();

        Account savedAccount = repo.save(account);
        JoinDto joinDtoFromAccount = JoinDto.from(savedAccount);
        log.info("joinDtoFromAccount 가입 성공 {}", joinDtoFromAccount.getAccountId());
        return joinDtoFromAccount;
    }
}



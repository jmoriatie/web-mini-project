package com.api.miniproject.service.account;

import com.api.miniproject.domain.Account;
import com.api.miniproject.domain.Authority;
import com.api.miniproject.dto.account.JoinDto;
import com.api.miniproject.exception.exceptionModel.ErrorCode;
import com.api.miniproject.exception.exceptions.CustomException;
import com.api.miniproject.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Account saveAccount(JoinDto joinDto) {
        JoinDto accountDto = this.createAccount(joinDto, null);
        return Account.from(accountDto);
    }

    public Account findByAccountId(String accountId){
        return repo.findByAccountId(accountId).orElse(null);
    }

    @Transactional
    public JoinDto join(JoinDto joinDto){
        this.accountCheck(joinDto);

        JoinDto joinDtoFromAccount = this.createAccount(joinDto, null);
        log.info("가입 성공 {}", joinDtoFromAccount.getAccountId());
        return joinDtoFromAccount;
    }

    @Transactional
    public JoinDto createUserForAdmin(JoinDto joinDto){
        this.accountCheck(joinDto);

        Set<Authority> authorities = joinDto.getAuthorities().stream()
                .map(role -> Authority.builder().authorityName("ROLE_"+role).build())
                .collect(Collectors.toSet());

        JoinDto createdAccount = this.createAccount(joinDto, authorities);
        log.info("유저생성 성공 {}", createdAccount.getAccountId());
        return createdAccount;
    }

    private JoinDto createAccount(JoinDto joinDto, @Nullable Set<Authority> authorities) {

        if(authorities == null){ // 넘어온 role이 없을 경우
            authorities = new HashSet<>();
            authorities.add(Authority.builder()
                    .authorityName("ROLE_USER").build());
        }

        Account account = Account.builder()
                .accountId(joinDto.getAccountId())
                .accountPw(passwordEncoder.encode(joinDto.getAccountPw()))
                .accountName(joinDto.getAccountName())
                .authorities(authorities)
                .build();

        Account savedAccount = repo.save(account);
        return JoinDto.from(savedAccount);
    }

    private void accountCheck(JoinDto joinDto) {
        if(repo.findByAccountId(joinDto.getAccountId()).isPresent()){
            throw new CustomException("이미 있는 계정입니다.", ErrorCode.ALREADY_IN_ACCOUNT);
        }
    }
}



package com.api.miniproject.service.account;

import com.api.miniproject.domain.Account;
import com.api.miniproject.repository.AccountRepository;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class CustomUserDetailService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public CustomUserDetailService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String accountId) {
        return accountRepository.findOneWithAuthoritiesByAccountId(accountId)
                .map(this::createUser)
                .orElseThrow(() -> new UsernameNotFoundException(accountId + " 없는 유저입니다."));
    }

    private User createUser(Account account) {

        List<GrantedAuthority> grantedAuthorities = account.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());

        return new User(account.getAccountId(), account.getAccountPw(), grantedAuthorities);
    }
}

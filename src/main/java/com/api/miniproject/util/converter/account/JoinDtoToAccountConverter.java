package com.api.miniproject.util.converter.account;

import com.api.miniproject.domain.Account;
import com.api.miniproject.domain.Authority;
import com.api.miniproject.dto.account.JoinDto;
import org.springframework.core.convert.converter.Converter;

import java.util.Collections;

public class JoinDtoToAccountConverter implements Converter<JoinDto, Account> {

    @Override
    public Account convert(JoinDto source) {
        Authority role = Authority.builder()
                .authorityName("ROLE_USER")
                .build();
        
        return Account.builder()
                .accountId(source.getAccountId())
                .accountPw(source.getAccountPw())
                .accountName(source.getAccountName())
                .authorities(Collections.singleton(role))
                .build();
    }
}

package com.api.miniproject.util.converter.account;

import com.api.miniproject.domain.Account;
import com.api.miniproject.dto.account.JoinDto;
import org.springframework.core.convert.converter.Converter;

public class JoinDtoToAccountConverter implements Converter<JoinDto, Account> {

    @Override
    public Account convert(JoinDto source) {
        return Account.builder()
                .accountId(source.getAccountId())
                .accountPw(source.getAccountPw())
                .accountName(source.getAccountName())
                .build();
    }
}

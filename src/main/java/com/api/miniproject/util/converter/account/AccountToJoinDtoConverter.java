package com.api.miniproject.util.converter.account;

import com.api.miniproject.domain.Account;
import com.api.miniproject.dto.account.JoinDto;
import org.springframework.core.convert.converter.Converter;

public class AccountToJoinDtoConverter implements Converter<Account, JoinDto> {

    @Override
    public JoinDto convert(Account source) {
        return JoinDto.builder()
                .accountId(source.getAccountId())
                .accountName(source.getAccountId())
                .build();
    }
}

package com.api.miniproject.util.converter.item;

import com.api.miniproject.domain.User;
import com.api.miniproject.dto.login.JoinDto;
import com.api.miniproject.util.StatusEnum;
import org.springframework.core.convert.converter.Converter;

public class UserToJoinDtoConverter implements Converter<User, JoinDto> {

    @Override
    public JoinDto convert(User source) {
        return JoinDto.builder()
                .userId(source.getUserId())
                .userName(source.getUserName())
                .build();
    }
}

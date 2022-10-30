package com.api.miniproject.util.converter.user;

import com.api.miniproject.domain.User;
import com.api.miniproject.dto.user.JoinDto;
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

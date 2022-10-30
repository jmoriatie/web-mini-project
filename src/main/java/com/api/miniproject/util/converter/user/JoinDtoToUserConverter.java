package com.api.miniproject.util.converter.user;

import com.api.miniproject.domain.User;
import com.api.miniproject.dto.user.JoinDto;
import org.springframework.core.convert.converter.Converter;

public class JoinDtoToUserConverter implements Converter<JoinDto, User> {

    @Override
    public User convert(JoinDto source) {
        return User.builder()
                .userId(source.getUserId())
                .userPw(source.getUserPw())
                .userName(source.getUserName())
                .build();
    }
}

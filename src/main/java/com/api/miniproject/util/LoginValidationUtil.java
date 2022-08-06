package com.api.miniproject.util;

import com.api.miniproject.dto.LoginDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;


@Slf4j
public class LoginValidationUtil {
    // 로그인 validation 해주는 유틸
    // 공백삭제 등등 여러가지 넣어봐야할듯

    public LoginValidationUtil() {
    }

    public boolean validation(LoginDto loginDto, BindingResult bindingResult){
        boolean check = false;
        if(!StringUtils.hasText(loginDto.getUserId())){
            log.debug("id 문제");
            bindingResult.rejectValue("userId", "userIdError", "아이디를 입력해주세요");
            check = true;
        }

        if(!StringUtils.hasText(loginDto.getUserPw())){
            log.debug("pw 문제");
            bindingResult.rejectValue("userPw", "userPwError", "비밀번호를 입력해주세요");
            check = true;
        }

        return check;
    }


}

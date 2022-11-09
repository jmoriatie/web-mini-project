package com.api.miniproject.util.validation;

import com.api.miniproject.domain.Account;
import com.api.miniproject.dto.login.LoginDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

/**
 * 사용 X
 */
@Slf4j
public class LoginValidationUtil {
    // 로그인 validation 해주는 유틸
    // 공백삭제 등등 여러가지 넣어봐야할듯
    public static boolean validation(LoginDto loginDto, Account account, BindingResult bindingResult){
        boolean masterCheck;
        masterCheck = emptyBoxCheck(loginDto, bindingResult);
        masterCheck = userNotExist(account, bindingResult);

        return masterCheck;
    }

    static boolean emptyBoxCheck(LoginDto loginDto, BindingResult bindingResult){
        boolean check = false;
        if(!StringUtils.hasText(loginDto.getUserId())){
            log.debug("id 문제");
            bindingResult.rejectValue("userId", "아이디를 입력해주세요");
            check = true;
        }
        if(!StringUtils.hasText(loginDto.getUserPw())){
            log.debug("pw 문제");
            bindingResult.rejectValue("userPw", "비밀번호를 입력해주세요");
            check = true;
        }
        return check;
    }

    static boolean userNotExist(Account account, BindingResult bindingResult){
        boolean check = true;
        if(account == null){
            bindingResult.reject("userNotExist",  "아이디 또는 비밀번호를 확인하세요");
            check = false;
        }
        return check;
    }

}

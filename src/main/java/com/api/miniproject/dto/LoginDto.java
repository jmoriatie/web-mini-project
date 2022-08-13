package com.api.miniproject.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter
public class LoginDto {

//    @NotBlank(message = "아이디를 입력하세요")
    @NotBlank
    String userId;

//    @NotBlank(message = "비밀번호를 입력하세요")
//    @Size(min=2, message = "비밀번호는 두글자 이상 입력하세요")
    @NotBlank
    String userPw;

    public LoginDto() {
    }

    public LoginDto(String userId, String userPw) {
        this.userId = userId;
        this.userPw = userPw;
    }

    @Override
    public String toString() {
        return "LoginDto{" +
                "userId='" + userId + '\'' +
                ", userPw='" + userPw + '\'' +
                '}';
    }
}

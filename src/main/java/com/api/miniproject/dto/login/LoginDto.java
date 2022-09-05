package com.api.miniproject.dto.login;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter
public class LoginDto {

    @NotBlank
    String userId;

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

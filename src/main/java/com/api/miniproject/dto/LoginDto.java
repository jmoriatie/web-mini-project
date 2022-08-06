package com.api.miniproject.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

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
}

package com.api.miniproject.dto.login;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter @Setter
public class LoginDto {

    @NotBlank
    String userId;

    @NotBlank
//    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}") // FIXME: TEST 이후 해제
    String userPw;

    public LoginDto() {
    }

    public LoginDto(String userId, String userPw) {
        this.userId = userId;
        this.userPw = userPw;
    }
}

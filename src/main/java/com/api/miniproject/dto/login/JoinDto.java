package com.api.miniproject.dto.login;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter @Setter
@NoArgsConstructor
public class JoinDto {

    @NotBlank
    String userId;

    @NotBlank
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}")
    String userPw;

    @NotEmpty
    String userName;

    @Builder
    public JoinDto(String userId, String userPw, String userName) {
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
    }
}

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
    @Column(name = "USER_ID")
    String userId;

    @NotBlank
    @Column(name = "USER_PW")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}")
    String userPw;

    @NotEmpty
    @Column(name = "USER_NAME")
    String userName;

    @Builder
    public JoinDto(String userId, String userPw, String userName) {
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
    }
}

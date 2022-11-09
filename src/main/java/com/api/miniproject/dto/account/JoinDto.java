package com.api.miniproject.dto.account;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter @Setter
@NoArgsConstructor
public class JoinDto {

    @NotBlank
    String accountId;

    @NotBlank
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}")
    String accountPw;

    @NotEmpty
    String accountName;

    @Builder
    public JoinDto(String accountId, String accountPw, String accountName) {
        this.accountId = accountId;
        this.accountPw = accountPw;
        this.accountName = accountName;
    }
}

package com.api.miniproject.dto.account;

import com.api.miniproject.domain.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class LoginDto {

    @NotBlank
    private String accountId;

    @NotBlank
//    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}") // FIXME: TEST 이후 해제
    private String accountPw;

    @Builder
    public LoginDto(String accountId, String accountPw) {
        this.accountId = accountId;
        this.accountPw = accountPw;
    }

    public static LoginDto from(Account account){
        return LoginDto.builder()
                .accountId(account.getAccountId())
                .accountPw(account.getAccountPw()).build();
    }
}

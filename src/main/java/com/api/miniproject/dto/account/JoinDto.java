package com.api.miniproject.dto.account;

import com.api.miniproject.domain.Account;
import com.api.miniproject.domain.Authority;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class JoinDto {

    @NotBlank
    private String accountId;

    @NotBlank
    @Column(length = 100)
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}")
    private String accountPw;

    @NotEmpty
    private String accountName;

    private Set<AuthorityDto> authorities;

    @Builder
    public JoinDto(String accountId, String accountPw, String accountName, Set<AuthorityDto> authorities) {
        this.accountId = accountId;
        this.accountPw = accountPw;
        this.accountName = accountName;
        this.authorities = authorities;
    }

    public static JoinDto from(Account account) {
        return JoinDto.builder()
                .accountId(account.getAccountId())
                .accountPw(account.getAccountPw())
                .accountName(account.getAccountName())
                .authorities(account.getAuthorities().stream()
                        .map( authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
                        .collect(Collectors.toSet()) )
                .build();
    }
}

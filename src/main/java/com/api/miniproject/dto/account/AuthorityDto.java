package com.api.miniproject.dto.account;

import lombok.*;

@Getter
@NoArgsConstructor
public class AuthorityDto {
    private String authorityName;

    @Builder
    public AuthorityDto(String authorityName) {
        this.authorityName = authorityName;
    }
}

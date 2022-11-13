package com.api.miniproject.dto.auth;

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

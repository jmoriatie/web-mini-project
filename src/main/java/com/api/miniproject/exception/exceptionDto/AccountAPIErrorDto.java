package com.api.miniproject.exception.exceptionDto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AccountAPIErrorDto {

    private final int status;
    private final String message;

    @Builder
    public AccountAPIErrorDto(int status, String message) {
        this.status = status;
        this.message = message;
    }
}


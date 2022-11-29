package com.api.miniproject.exception.exceptions;

public class AccountAPIException extends NullPointerException {

    public AccountAPIException() {
        super("정상적인 요청이 아닙니다.");
    }
}

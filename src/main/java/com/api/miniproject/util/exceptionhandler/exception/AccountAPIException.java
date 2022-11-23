package com.api.miniproject.util.exceptionhandler.exception;

public class AccountAPIException extends NullPointerException {

    public AccountAPIException() {
        super("없는 계정 입니다.");
    }
}

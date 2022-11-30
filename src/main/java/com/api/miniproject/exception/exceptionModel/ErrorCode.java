package com.api.miniproject.exception.exceptionModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode implements EnumModel{

    //server
    TEMPORARY_SERVER_ERROR(500, "A000", "TEMPORARY SERVER ERROR"),

    //account
    ALREADY_IN_ACCOUNT(400,"A001","ALREADY IN ACCOUNT"),
    NOT_EXIST_ACCOUNT(400, "A002", "NOT_EXIST_ACCOUNT");

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    @Override
    public String getKey() {
        return this.code;
    }

    @Override
    public String getValue() {
        return this.message;
    }
}

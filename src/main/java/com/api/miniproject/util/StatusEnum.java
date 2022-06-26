package com.api.miniproject.util;

public enum StatusEnum {

    OK(200, "OK"),
    BAD_REQUEST(400, "BAD_REQUEST"),
    NOT_FOUND(404, "NOT_FOUND"),
    INTERNAL_SERVER_ERROR(500,"INTERNAL_SERVER_ERROR");

    public final int statusCode;
    public final String statusMessage;

    StatusEnum(int statusCode, String statusMessage){
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

}

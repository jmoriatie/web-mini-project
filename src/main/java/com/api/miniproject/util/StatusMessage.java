package com.api.miniproject.util;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StatusMessage {

    private StatusEnum status;
    private String message;
    private Object data;

    public StatusMessage() {
        this.status = StatusEnum.BAD_REQUEST;
        this.message = null;
        this.data = null;
    }
}

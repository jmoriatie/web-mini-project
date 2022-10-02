package com.api.miniproject.util.exceptionhandler.exception;

import org.springframework.validation.*;

public class ItemAPIBindException extends BindException {

    public ItemAPIBindException(BindingResult bindingResult) {
        super(bindingResult);
    }
}

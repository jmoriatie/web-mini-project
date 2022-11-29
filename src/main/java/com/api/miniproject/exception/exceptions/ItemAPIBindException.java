package com.api.miniproject.exception.exceptions;

import org.springframework.validation.*;

public class ItemAPIBindException extends BindException {

    public ItemAPIBindException(BindingResult bindingResult) {
        super(bindingResult);
    }
}

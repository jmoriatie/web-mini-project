package com.api.miniproject.util.exceptionhandler;

import com.api.miniproject.util.exceptionhandler.dto.AccountAPIErrorDto;
import com.api.miniproject.util.exceptionhandler.dto.ItemAPIErrorDto;
import com.api.miniproject.util.exceptionhandler.exception.AccountAPIException;
import com.api.miniproject.util.exceptionhandler.exception.ItemAPIBindException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class ItemRestControllerExAdvice {

    private final MessageSource messageSource;

    public ItemRestControllerExAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ItemAPIBindException.class)
    public ItemAPIErrorDto itemApiErrorResolver(ItemAPIBindException ex){
        log.error("ItemAPIBindException 발생!",ex);
        List<FieldError> fieldErrors = ex.getFieldErrors();
        return ItemAPIErrorDto.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .errors(fieldErrors)
                .messageSource(messageSource).build();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = AccountAPIException.class)
    public AccountAPIErrorDto accountAPIException(AccountAPIException ex){
        log.error("AccountAPIException 발생!", ex);
        return AccountAPIErrorDto.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage()).build();
    }


}

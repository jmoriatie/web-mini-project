package com.api.miniproject.util.exceptionhandler;

import com.api.miniproject.util.exceptionhandler.dto.ItemApiErrorResult;
import com.api.miniproject.util.exceptionhandler.exception.ItemAPIBindException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@Slf4j
@RestControllerAdvice
public class ItemRestControllerExAdvice {

    private final MessageSource messageSource;

    public ItemRestControllerExAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ItemAPIBindException.class)
    public ItemApiErrorResult itemApiErrorResolver(ItemAPIBindException ex){
        log.error("error 발생!",ex);
        log.info("message source={}", messageSource);

        return ItemApiErrorResult.create(ex, messageSource, Locale.KOREA);
    }
}

package com.api.miniproject.util.exceptionhandler.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.MessageSource;
import org.springframework.validation.FieldError;

import java.util.Locale;

@Getter @Setter
@AllArgsConstructor
public class ItemApiErrorInfo {
    private String field; // 에러발생 필드이름
    private String code; // 에러발생 코드
    private Object value; // 에러발생 값
    private String message; // 에러메세지 MessageSource 에서 가지고 있는 것 꺼내기

    public static ItemApiErrorInfo create(FieldError fieldError, MessageSource messageSource, Locale locale){
        return new ItemApiErrorInfo(
                fieldError.getField(),
                fieldError.getCode(),
                fieldError.getRejectedValue(),
                messageSource.getMessage(fieldError, locale)
                );
    }
}

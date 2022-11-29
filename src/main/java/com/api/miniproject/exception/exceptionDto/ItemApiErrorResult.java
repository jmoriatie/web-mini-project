package com.api.miniproject.exception.exceptionDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.MessageSource;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Getter @Setter
@AllArgsConstructor
public class ItemApiErrorResult {

    private List<ItemApiErrorInfo> errorList;

    public static ItemApiErrorResult create(Errors errors, MessageSource messageSource, Locale locale){
        List<ItemApiErrorInfo> errorList = errors.getFieldErrors()
                .stream()
                .map(error -> ItemApiErrorInfo.create(error, messageSource, locale))
                .collect(Collectors.toList());
        return new ItemApiErrorResult(errorList);
    }
}

package com.api.miniproject.exception.exceptionDto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.context.MessageSource;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Getter
public class ItemAPIErrorDto {

    private final int status;
    private List<String> messages;

    @Builder
    public ItemAPIErrorDto(int status, List<FieldError> errors, MessageSource messageSource) {
        this.status = status;
        this.messages = errors.stream()
                .map(e -> messageSource.getMessage(e, Locale.KOREA))
                .collect(Collectors.toList());
    }
}

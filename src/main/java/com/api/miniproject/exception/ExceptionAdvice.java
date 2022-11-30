package com.api.miniproject.exception;

import com.api.miniproject.exception.exceptionModel.ErrorCode;
import com.api.miniproject.exception.exceptionModel.ErrorResponse;
import com.api.miniproject.exception.exceptions.AccountAPIException;
import com.api.miniproject.exception.exceptions.CustomException;
import com.api.miniproject.exception.exceptions.ItemAPIBindException;
import com.api.miniproject.exception.exceptionDto.AccountAPIErrorDto;
import com.api.miniproject.exception.exceptionDto.ItemAPIErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class ExceptionAdvice implements ResponseBodyAdvice<Object> {

    private final MessageSource messageSource;

    public ExceptionAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<String> validationErrors(MethodArgumentNotValidException ex) {
        log.error("MethodArgumentNotValidException validation error 발생", ex);

        List<String> errorList = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .distinct()
                .map(exeption -> String.format("%s : %s", exeption.getField(), exeption.getDefaultMessage()))
                .collect(Collectors.toList());
        return errorList;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> customException(CustomException ex){
        log.error("CustomException 발생! ={}", ex.getMessage());
        ErrorResponse errorResponse = ErrorResponse.of(ex.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorResponse> handleException(Exception ex) {
        log.error("handleException 발생!", ex);
        ErrorResponse response = ErrorResponse.of(ErrorCode.TEMPORARY_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ItemAPIBindException.class)
    public ResponseEntity itemApiErrorResolver(ItemAPIBindException ex) {
        log.error("ItemAPIBindException 발생!", ex);
        return ResponseEntity.badRequest().body(
                ItemAPIErrorDto.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .errors(ex.getFieldErrors())
                        .messageSource(messageSource).build()
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = AccountAPIException.class)
    public ResponseEntity accountAPIException(AccountAPIException ex) {
        log.error("AccountAPIException 발생!", ex);
        return ResponseEntity.badRequest().body(
                AccountAPIErrorDto.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message(ex.getMessage()).build()
        );
    }


    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return converterType.getSimpleName().equals("MappingJackson2HttpMessageConverter");
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        return body;
    }
}

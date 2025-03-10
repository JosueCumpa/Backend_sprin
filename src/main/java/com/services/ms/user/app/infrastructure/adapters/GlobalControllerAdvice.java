package com.services.ms.user.app.infrastructure.adapters;

import com.services.ms.user.app.domain.exception.UserNotFountException;
import com.services.ms.user.app.domain.model.ErrorResponse;
import com.services.ms.user.app.utils.ErrorCatalog;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.Binding;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFountException.class)
    public ErrorResponse handleUserNotFoundException(){
            return ErrorResponse.builder()
                    .code(ErrorCatalog.USER_NOT_FOUND.getCode())
                    .message(ErrorCatalog.USER_NOT_FOUND.getMessage())
                    .timestamp(LocalDateTime.now())
                    .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception){
        BindingResult result= exception.getBindingResult();

        return ErrorResponse.builder()
                .code(ErrorCatalog.USER_INVALID.getCode())
                .message(ErrorCatalog.USER_INVALID.getMessage())
                .details(result.getFieldErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList()))
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleGenericError(Exception exception){
        return ErrorResponse.builder()
                .code(ErrorCatalog.GENERIC_ERROR.getCode())
                .message(ErrorCatalog.GENERIC_ERROR.getMessage())
                .details(Collections.singletonList(exception.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
    }
}

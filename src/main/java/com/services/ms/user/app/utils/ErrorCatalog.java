package com.services.ms.user.app.utils;

import lombok.Getter;

@Getter
public enum ErrorCatalog {
    USER_NOT_FOUND("ERR_USER_001", "user not found"),
    USER_INVALID("ERR_USER_002", "user invalid parameters"),
    GENERIC_ERROR("ERR_GEN_001","An unexpected error ocurred");

    private final String code;
    private final String message;

    ErrorCatalog(String code, String message){
        this.code= code;
        this.message = message;
    }
}

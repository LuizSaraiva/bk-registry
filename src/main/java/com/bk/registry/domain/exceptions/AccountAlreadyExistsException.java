package com.bk.registry.domain.exceptions;

import com.bk.registry.domain.exceptions.enums.ExceptionMessage;

import java.util.UUID;

public class AccountAlreadyExistsException extends BusinessException {

    public AccountAlreadyExistsException(String message) {
        super(message);
    }

    public AccountAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
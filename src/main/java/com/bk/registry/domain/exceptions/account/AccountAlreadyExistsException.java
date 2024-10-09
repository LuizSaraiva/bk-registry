package com.bk.registry.domain.exceptions.account;

import com.bk.registry.domain.exceptions.BusinessException;

public class AccountAlreadyExistsException extends BusinessException {

    public AccountAlreadyExistsException(String message) {
        super(message);
    }

    public AccountAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
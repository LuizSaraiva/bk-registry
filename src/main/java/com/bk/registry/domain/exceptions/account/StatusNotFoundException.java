package com.bk.registry.domain.exceptions.account;

import com.bk.registry.domain.exceptions.BusinessException;

public class StatusNotFoundException extends BusinessException {

    public StatusNotFoundException(String message) {
        super(message);
    }

    public StatusNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}

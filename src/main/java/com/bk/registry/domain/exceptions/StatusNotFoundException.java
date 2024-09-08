package com.bk.registry.domain.exceptions;

import java.util.UUID;

public class StatusNotFoundException extends BusinessException {

    public StatusNotFoundException(String message) {
        super(message);
    }

    public StatusNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}

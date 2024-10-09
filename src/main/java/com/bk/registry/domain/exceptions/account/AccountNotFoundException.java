package com.bk.registry.domain.exceptions.account;

import com.bk.registry.domain.exceptions.BusinessException;

import java.util.UUID;

public class AccountNotFoundException extends BusinessException {

    public AccountNotFoundException(String message) {
        super(message);
    }

    public AccountNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountNotFoundException(UUID id) {
        super(String.format("Account not found %s!", id.toString()));
    }

    public AccountNotFoundException(Integer branch, Long account) {
        super(String.format("account %s/%s not found!",
                branch, account));
    }
}

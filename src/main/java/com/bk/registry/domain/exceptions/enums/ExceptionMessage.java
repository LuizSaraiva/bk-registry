package com.bk.registry.domain.exceptions.enums;

public enum ExceptionMessage {

    ACCOUNT_NOT_FOUND("Account not found."),
    ACCOUNT_ALREADY_EXISTS("Account already exists, verify branch, account and document.");

    private String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}

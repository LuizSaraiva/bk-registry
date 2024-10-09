package com.bk.registry.domain.exceptions.historytransaction.enums;

public enum HistoryTransactionExceptionMessage {

    HISTORY_TRANSACTION_NOT_FOUND("History not found %s");

    private String message;

    HistoryTransactionExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}

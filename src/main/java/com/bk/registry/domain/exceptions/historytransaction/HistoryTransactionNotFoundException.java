package com.bk.registry.domain.exceptions.historytransaction;

import com.bk.registry.domain.exceptions.BusinessException;
import com.bk.registry.domain.exceptions.historytransaction.enums.HistoryTransactionExceptionMessage;

import java.util.UUID;

public class HistoryTransactionNotFoundException extends BusinessException {

    public HistoryTransactionNotFoundException(String message) {
        super(message);
    }

    public HistoryTransactionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public HistoryTransactionNotFoundException(UUID id) {
        super(
                String.format(
                        HistoryTransactionExceptionMessage.HISTORY_TRANSACTION_NOT_FOUND.getMessage(),
                        id.toString()));
    }
}

package com.bk.registry.mapper.dto.historytransaction.messaging;

import com.bk.registry.domain.enums.TypeMessageBroker;
import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.Data;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
public class HistoryTransactionMessageToBrokerDTO implements Serializable {
    private TypeMessageBroker type;
    @JsonRawValue
    private String historyTransaction;
    private OffsetDateTime sent_date;
}

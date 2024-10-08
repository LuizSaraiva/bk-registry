package com.bk.registry.mapper.dto.account.messaging;

import com.bk.registry.domain.enums.TypeMessageBroker;
import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@ToString
public class AccountMessageToBrokerDTO implements Serializable {
    private TypeMessageBroker type;
    @JsonRawValue
    private String account;
    private OffsetDateTime sent_date;
}

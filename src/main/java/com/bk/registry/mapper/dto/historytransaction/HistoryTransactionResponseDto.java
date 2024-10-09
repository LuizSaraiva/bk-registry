package com.bk.registry.mapper.dto.historytransaction;

import com.bk.registry.api.enums.historytransaction.TypeHistoryTransaction;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class HistoryTransactionResponseDto {

    private UUID id;
    private String description;
    private TypeHistoryTransaction type;
}

package com.bk.registry.mapper.dto.historytransaction;

import com.bk.registry.api.enums.historytransaction.TypeHistoryTransaction;
import lombok.Data;

import java.util.UUID;

@Data
public class HistoryTransactionResponseDto {

    private UUID idHistory;
    private String description;
    private TypeHistoryTransaction typeHistoryTransaction;
}

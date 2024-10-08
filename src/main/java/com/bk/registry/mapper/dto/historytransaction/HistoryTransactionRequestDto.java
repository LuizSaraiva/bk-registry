package com.bk.registry.mapper.dto.historytransaction;

import com.bk.registry.api.enums.historytransaction.TypeHistoryTransaction;
import lombok.Data;

import java.util.UUID;

@Data
public class HistoryTransactionRequestDto {
    private String description;
    private TypeHistoryTransaction type;
}

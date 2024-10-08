package com.bk.registry.mapper.dto.historytransaction.messaging;

import com.bk.registry.api.enums.historytransaction.TypeHistoryTransaction;
import lombok.Data;

import java.util.UUID;

@Data
public class HistoryTransactionMessageDTO {

    private UUID id;
    private String description;
    private TypeHistoryTransaction type;
}

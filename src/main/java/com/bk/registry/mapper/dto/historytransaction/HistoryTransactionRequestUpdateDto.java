package com.bk.registry.mapper.dto.historytransaction;

import com.bk.registry.api.enums.historytransaction.TypeHistoryTransaction;
import lombok.Data;

@Data
public class HistoryTransactionRequestUpdateDto {

    private String description;
    private TypeHistoryTransaction type;
}

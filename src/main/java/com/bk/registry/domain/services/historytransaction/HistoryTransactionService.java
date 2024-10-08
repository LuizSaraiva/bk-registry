package com.bk.registry.domain.services.historytransaction;

import com.bk.registry.domain.entity.historytransaction.HistoryTransaction;
import com.bk.registry.mapper.dto.historytransaction.HistoryTransactionRequestDto;
import com.bk.registry.mapper.dto.historytransaction.HistoryTransactionResponseDto;

import java.util.List;
import java.util.UUID;

public interface HistoryTransactionService {

    List<HistoryTransactionResponseDto> getAllHistoryTransaction();
    HistoryTransaction getHistoryTransactionById(UUID id);
    void updateHistoryTransaction(HistoryTransaction historyTransaction);
    HistoryTransaction saveHistoryTransaction(HistoryTransactionRequestDto historyTransactionRequestDto);
}

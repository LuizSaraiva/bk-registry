package com.bk.registry.domain.services.historytransaction;

import com.bk.registry.domain.entity.account.Account;
import com.bk.registry.domain.entity.historytransaction.HistoryTransaction;
import com.bk.registry.mapper.dto.account.AccountRequestUpdateDTO;
import com.bk.registry.mapper.dto.historytransaction.HistoryTransactionRequestDto;
import com.bk.registry.mapper.dto.historytransaction.HistoryTransactionRequestUpdateDto;
import com.bk.registry.mapper.dto.historytransaction.HistoryTransactionResponseDto;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.UUID;

public interface HistoryTransactionService {

    List<HistoryTransactionResponseDto> getAllHistoryTransaction();
    HistoryTransaction getHistoryTransactionById(UUID id);
    HistoryTransactionResponseDto updateHistoryTransaction(UUID idHistory, HistoryTransactionRequestUpdateDto historyTransactionRequestUpdateDto);
    HistoryTransaction saveHistoryTransaction(HistoryTransactionRequestDto historyTransactionRequestDto);

}

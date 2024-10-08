package com.bk.registry.api.controller.historytransaction;

import com.bk.registry.domain.services.historytransaction.HistoryTransactionService;
import com.bk.registry.mapper.dto.historytransaction.HistoryTransactionRequestDto;
import com.bk.registry.mapper.dto.historytransaction.HistoryTransactionRequestUpdateDto;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/history")
@Log4j2
public class HistoryTransactionTransactionController implements HistoryTransactionControllerApi {

    @Autowired
    private HistoryTransactionService historyTransactionService;


    @Override
    public ResponseEntity<?> getAllHistories() {
        return null;
    }

    @Override
    public ResponseEntity<?> getHistory(UUID idHistory) {
        return null;
    }

    @Override
    public ResponseEntity<?> createHistory(HistoryTransactionRequestDto historyTransactionRequestDto) {
        val historyTransaction = historyTransactionService.saveHistoryTransaction(historyTransactionRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(historyTransaction);
    }

    @Override
    public ResponseEntity<?> updateHistory(UUID idHistory, HistoryTransactionRequestUpdateDto historyTransactionRequestUpdateDto) {
        return null;
    }
}

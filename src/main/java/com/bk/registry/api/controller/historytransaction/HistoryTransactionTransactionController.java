package com.bk.registry.api.controller.historytransaction;

import com.bk.registry.domain.services.historytransaction.HistoryTransactionService;
import com.bk.registry.mapper.dto.historytransaction.HistoryTransactionRequestDto;
import com.bk.registry.mapper.dto.historytransaction.HistoryTransactionRequestUpdateDto;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/history")
@Log4j2
public class HistoryTransactionTransactionController implements HistoryTransactionControllerApi {

    private HistoryTransactionService historyTransactionService;

    public HistoryTransactionTransactionController(HistoryTransactionService historyTransactionService) {
        this.historyTransactionService = historyTransactionService;
    }

    @Override
    public ResponseEntity<?> getAllHistories() {
        return ResponseEntity.ok(historyTransactionService.getAllHistoryTransaction());
    }

    @Override
    public ResponseEntity<?> getHistory(UUID idHistory) {
        return null;
    }

    @Override
    public ResponseEntity<?> createHistory(HistoryTransactionRequestDto historyTransactionRequestDto) {
        log.info("Received request to create history: {}", historyTransactionRequestDto);
        try {
            val historyTransaction = historyTransactionService.saveHistoryTransaction(historyTransactionRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(historyTransaction);
        }catch (Exception ex){
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateHistory(UUID idHistory, HistoryTransactionRequestUpdateDto historyTransactionRequestUpdateDto) {
        return null;
    }
}

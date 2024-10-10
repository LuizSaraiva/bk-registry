package com.bk.registry.api.controller.historytransaction;

import com.bk.registry.domain.entity.historytransaction.HistoryTransaction;
import com.bk.registry.domain.exceptions.historytransaction.HistoryTransactionNotFoundException;
import com.bk.registry.domain.services.historytransaction.HistoryTransactionService;
import com.bk.registry.mapper.dto.historytransaction.HistoryTransactionRequestDto;
import com.bk.registry.mapper.dto.historytransaction.HistoryTransactionRequestUpdateDto;
import com.bk.registry.mapper.dto.historytransaction.HistoryTransactionResponseDto;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
        log.info("Received request to get all histories transactions.");
        List<HistoryTransactionResponseDto> historyTransactionResponseDtoList = historyTransactionService.getAllHistoryTransaction();
        log.info("Response request to get all histories transactions.");
        return ResponseEntity.ok(historyTransactionResponseDtoList);
    }

    @Override
    public ResponseEntity<?> getHistory(UUID idHistory) {
        log.info("Received request to get history {}.", idHistory);
        HistoryTransaction historyTransaction = historyTransactionService.getHistoryTransactionById(idHistory);
        log.info("Response request to get history {}.", idHistory);
        return ResponseEntity.ok(historyTransaction);
    }

    @Override
    public ResponseEntity<?> createHistory(HistoryTransactionRequestDto historyTransactionRequestDto) {
        log.info("Received request to create history: {}", historyTransactionRequestDto);
        HistoryTransaction historyTransaction = historyTransactionService.saveHistoryTransaction(historyTransactionRequestDto);
        log.info("Response request to create history: {}", historyTransaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(historyTransaction);
    }

    @Override
    public ResponseEntity<?> updateHistory(UUID idHistory, HistoryTransactionRequestUpdateDto historyTransactionRequestUpdateDto) {
        log.info("Received request to update history: {}", idHistory);
        HistoryTransactionResponseDto historyTransactionResponseDto = historyTransactionService.updateHistoryTransaction(idHistory, historyTransactionRequestUpdateDto);
        log.info("Response request to update history {} : {}", idHistory, historyTransactionResponseDto);
        return ResponseEntity.ok(historyTransactionResponseDto);
    }
}

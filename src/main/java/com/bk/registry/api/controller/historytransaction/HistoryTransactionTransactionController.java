package com.bk.registry.api.controller.historytransaction;

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
        try{
            List<HistoryTransactionResponseDto> historyTransactionResponseDtoList = historyTransactionService.getAllHistoryTransaction();
            log.info("Response request to get all histories transactions.");
            return ResponseEntity.ok(historyTransactionResponseDtoList);
        }catch (Exception ex){
            log.info(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> getHistory(UUID idHistory) {
        log.info("Received request to get history {}.", idHistory);

        try {
            log.info("Response request to get history {}.", idHistory);
            return ResponseEntity.ok(historyTransactionService.getHistoryTransactionById(idHistory));
        }catch (HistoryTransactionNotFoundException ex){
            log.error(ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> createHistory(HistoryTransactionRequestDto historyTransactionRequestDto) {
        log.info("Received request to create history: {}", historyTransactionRequestDto);
        try {
            val historyTransaction = historyTransactionService.saveHistoryTransaction(historyTransactionRequestDto);
            log.info("Response request to create history: {}",historyTransaction);
            return ResponseEntity.status(HttpStatus.CREATED).body(historyTransaction);
        }catch (Exception ex){
            log.error(ex.getMessage());
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateHistory(UUID idHistory, HistoryTransactionRequestUpdateDto historyTransactionRequestUpdateDto) {

        log.info("Received request to update history: {}", idHistory);
        try{
            val historyTransactionResponseDto = historyTransactionService.updateHistoryTransaction(idHistory, historyTransactionRequestUpdateDto);
            log.info("Response request to update history {} : {}",idHistory, historyTransactionResponseDto);
            return ResponseEntity.ok(historyTransactionResponseDto);
        }catch (Exception ex){
            log.error(ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}

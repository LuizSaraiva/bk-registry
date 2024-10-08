package com.bk.registry.api.controller.historytransaction;

import com.bk.registry.mapper.dto.historytransaction.HistoryTransactionRequestDto;
import com.bk.registry.mapper.dto.historytransaction.HistoryTransactionRequestUpdateDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/history")
@Log4j2
public class HistoryTransactionTransactionController implements HistoryTransactionControllerApi {

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
        return null;
    }

    @Override
    public ResponseEntity<?> updateHistory(UUID idHistory, HistoryTransactionRequestUpdateDto historyTransactionRequestUpdateDto) {
        return null;
    }
}

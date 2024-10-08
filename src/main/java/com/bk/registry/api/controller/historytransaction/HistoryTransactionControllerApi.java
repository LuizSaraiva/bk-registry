package com.bk.registry.api.controller.historytransaction;

import com.bk.registry.mapper.dto.historytransaction.HistoryTransactionRequestDto;
import com.bk.registry.mapper.dto.historytransaction.HistoryTransactionRequestUpdateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public interface HistoryTransactionControllerApi {

    @GetMapping
    ResponseEntity<?> getAllHistories();

    @GetMapping(value = "/{idHistory}")
    ResponseEntity<?> getHistory(@PathVariable("idHistory") UUID idHistory);

    @PostMapping
    ResponseEntity<?> createHistory(@RequestBody HistoryTransactionRequestDto historyTransactionRequestDto);

    @PutMapping(value = "/{idHistory}")
    ResponseEntity<?> updateHistory(@PathVariable("idHistory") UUID idHistory,
                                    @RequestBody HistoryTransactionRequestUpdateDto historyTransactionRequestUpdateDto);
}

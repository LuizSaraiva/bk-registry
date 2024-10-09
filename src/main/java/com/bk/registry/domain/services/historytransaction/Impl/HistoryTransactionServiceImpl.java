package com.bk.registry.domain.services.historytransaction.Impl;

import com.bk.registry.convert.JsonConverter;
import com.bk.registry.domain.entity.account.OutboxRegistry;
import com.bk.registry.domain.entity.historytransaction.HistoryTransaction;
import com.bk.registry.domain.enums.TypeEvent;
import com.bk.registry.domain.enums.TypeOutbox;
import com.bk.registry.domain.exceptions.historytransaction.HistoryTransactionNotFoundException;
import com.bk.registry.domain.exceptions.historytransaction.enums.HistoryTransactionExceptionMessage;
import com.bk.registry.domain.repositories.historytransaction.HistoryTransactionRepository;
import com.bk.registry.domain.services.OutboxRegistryService;
import com.bk.registry.domain.services.historytransaction.HistoryTransactionService;
import com.bk.registry.mapper.HistoryTransactionMapper;
import com.bk.registry.mapper.OutboxMapper;
import com.bk.registry.mapper.dto.HistoryTransactionMessageMapper;
import com.bk.registry.mapper.dto.historytransaction.HistoryTransactionRequestDto;
import com.bk.registry.mapper.dto.historytransaction.HistoryTransactionRequestUpdateDto;
import com.bk.registry.mapper.dto.historytransaction.HistoryTransactionResponseDto;
import com.bk.registry.mapper.dto.historytransaction.messaging.HistoryTransactionMessageDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class HistoryTransactionServiceImpl implements HistoryTransactionService {

    private HistoryTransactionRepository historyTransactionRepository;
    private HistoryTransactionMessageMapper historyTransactionMessageMapper;
    private HistoryTransactionMapper historyTransactionMapper;
    private OutboxRegistryService outboxRegistryService;
    private OutboxMapper outboxMapper;
    private JsonConverter jsonConverter;

    public HistoryTransactionServiceImpl(HistoryTransactionRepository historyTransactionRepository, HistoryTransactionMessageMapper historyTransactionMessageMapper, HistoryTransactionMapper historyTransactionMapper, OutboxRegistryService outboxRegistryService, OutboxMapper outboxMapper, JsonConverter jsonConverter) {
        this.historyTransactionRepository = historyTransactionRepository;
        this.historyTransactionMessageMapper = historyTransactionMessageMapper;
        this.historyTransactionMapper = historyTransactionMapper;
        this.outboxRegistryService = outboxRegistryService;
        this.outboxMapper = outboxMapper;
        this.jsonConverter = jsonConverter;
    }

    @Override
    public List<HistoryTransactionResponseDto> getAllHistoryTransaction() {
        val listHistoryTransaction = historyTransactionRepository.findAll();
        return historyTransactionMapper.listHistoryTransactionDomainToDto(listHistoryTransaction);
    }

    @Override
    public HistoryTransaction getHistoryTransactionById(UUID id) {
        return historyTransactionRepository.findById(id).orElseThrow(
                () -> new HistoryTransactionNotFoundException(id));
    }

    @Override
    public HistoryTransactionResponseDto updateHistoryTransaction(UUID idHistory, HistoryTransactionRequestUpdateDto historyTransactionRequestUpdateDto) {

        val historyTransactionFound = getHistoryTransactionById(idHistory);

        historyTransactionMapper.copyHistoryTransactionDtoUpdateToDomain(historyTransactionRequestUpdateDto, historyTransactionFound);
        val historySaved = historyTransactionRepository.save(historyTransactionFound);

        saveOutbox(historySaved, TypeEvent.UPDATE);
        return historyTransactionMapper.historyTransactionDomainToDto(historySaved);
    }

    @Override
    @Transactional
    public HistoryTransaction saveHistoryTransaction(HistoryTransactionRequestDto historyTransactionRequestDto) {
        var historyTransaction = historyTransactionMapper.historyTransactionDtoToDomain(historyTransactionRequestDto);
        historyTransaction.setCreate_date(OffsetDateTime.now());
        val historyTransactionSaved = historyTransactionRepository.save(historyTransaction);

        saveOutbox(historyTransactionSaved, TypeEvent.CREATE);
        return historyTransactionSaved;
    }

    private void saveOutbox(HistoryTransaction historyTransaction, TypeEvent typeEvent) {
        OutboxRegistry outboxRegistry = createOutbox(historyTransaction, typeEvent);
        outboxRegistryService.saveOutbox(outboxRegistry);
    }

    private OutboxRegistry createOutbox(HistoryTransaction historyTransaction, TypeEvent typeEvent) {

        HistoryTransactionMessageDTO historyTransactionMessageDTO = historyTransactionMessageMapper.historyTransactionDomainToMessage(historyTransaction);

        OutboxRegistry outboxRegistry = outboxMapper.historyTransactionDomainToOutbox(historyTransactionMessageDTO);
        outboxRegistry.setType(TypeOutbox.HISTORY);
        outboxRegistry.setUpdate_date(OffsetDateTime.now());
        outboxRegistry.setCreate_date(OffsetDateTime.now());
        outboxRegistry.setTypeEvent(typeEvent);

        try {
            String json = jsonConverter.toJson(historyTransactionMessageDTO);
            outboxRegistry.setMessage(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return outboxRegistry;
    }
}
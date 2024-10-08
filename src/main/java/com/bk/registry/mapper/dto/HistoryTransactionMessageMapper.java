package com.bk.registry.mapper.dto;

import com.bk.registry.domain.entity.account.OutboxRegistry;
import com.bk.registry.domain.entity.historytransaction.HistoryTransaction;
import com.bk.registry.mapper.dto.historytransaction.messaging.HistoryTransactionMessageDTO;
import com.bk.registry.mapper.dto.historytransaction.messaging.HistoryTransactionMessageToBrokerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface HistoryTransactionMessageMapper {
    @Mapping(target = "type", source = "typeEvent")
    @Mapping(target = "historyTransaction", source = "message")
    @Mapping(target = "sent_date", source = "update_date")
    HistoryTransactionMessageToBrokerDTO historyTransactionMessageDTOToMessageBroker(OutboxRegistry outboxRegistry);
    HistoryTransactionMessageDTO historyTransactionDomainToMessage(HistoryTransaction historyTransaction);
}

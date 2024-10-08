package com.bk.registry.mapper;

import com.bk.registry.domain.entity.account.OutboxRegistry;
import com.bk.registry.mapper.dto.account.messaging.AccountMessageDTO;
import com.bk.registry.mapper.dto.historytransaction.messaging.HistoryTransactionMessageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OutboxMapper {

    @Mapping(target = "type", ignore = true)
    @Mapping(target = "idRef", source = "id")
    @Mapping(target = "sent", ignore = true, defaultValue = "false")
    OutboxRegistry accountDomainToOutbox(AccountMessageDTO accountMessageDTO);

    @Mapping(target = "type", ignore = true)
    @Mapping(target = "idRef", source = "id")
    @Mapping(target = "sent", ignore = true, defaultValue = "false")
    OutboxRegistry historyTransactionDomainToOutbox(HistoryTransactionMessageDTO historyTransactionMessageDTO);

}

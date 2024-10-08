package com.bk.registry.mapper;

import com.bk.registry.domain.entity.Account;
import com.bk.registry.domain.entity.OutboxRegistry;
import com.bk.registry.mapper.dto.messaging.AccountMessageDTO;
import com.bk.registry.mapper.dto.messaging.MessageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AccountMessageMapper {

    @Mapping(target = "type", source = "typeEvent")
    @Mapping(target = "account", source = "message")
    @Mapping(target = "sent_date", source = "update_date")
    MessageDTO accountMessageDTOToMessageBroker(OutboxRegistry outboxRegistry);
    AccountMessageDTO accountDomainToMessage(Account account);
}

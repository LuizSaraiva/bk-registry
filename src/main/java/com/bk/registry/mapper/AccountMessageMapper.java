package com.bk.registry.mapper;

import com.bk.registry.domain.entity.account.Account;
import com.bk.registry.domain.entity.account.OutboxRegistry;
import com.bk.registry.mapper.dto.account.messaging.AccountMessageDTO;
import com.bk.registry.mapper.dto.account.messaging.MessageDTO;
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

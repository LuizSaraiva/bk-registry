package com.bk.registry.mapper;

import com.bk.registry.domain.entity.Account;
import com.bk.registry.domain.entity.OutboxRegistry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OutboxMapper {

    @Mapping(target = "type", ignore = true)
    @Mapping(target = "idRef", source = "id")
    @Mapping(target = "sent", ignore = true, defaultValue = "false")
    OutboxRegistry accountDomainToOutbox(Account account);
}

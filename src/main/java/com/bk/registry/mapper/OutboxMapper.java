package com.bk.registry.mapper;

import com.bk.registry.domain.entity.account.OutboxRegistry;
import com.bk.registry.mapper.dto.account.messaging.AccountMessageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OutboxMapper {

    @Mapping(target = "type", ignore = true)
    @Mapping(target = "idRef", source = "id")
    @Mapping(target = "sent", ignore = true, defaultValue = "false")
//    @Mapping(target = "message", source = "accountMessageDTO")
    OutboxRegistry accountDomainToOutbox(AccountMessageDTO accountMessageDTO);

//    @Named("accountMessageDTOToString")
//    static String pedidoDTOToString(AccountMessageDTO accountMessageDTO) {
//        try {
//            return new ObjectMapper().writeValueAsString(accountMessageDTO);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException("Error to serializer AccountMessage", e);
//        }
//    }
}

package com.bk.registry.mapper;

import com.bk.registry.api.enums.account.StatusAccountApi;
import com.bk.registry.domain.enums.account.StatusAccount;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatusAccountMapper {

    StatusAccount statusApiToDomain(StatusAccountApi statusAccountApi);
    StatusAccountApi statusDomainToApi(StatusAccount statusAccount);
    StatusAccountApi statusStringTostatusApi(String status);
}

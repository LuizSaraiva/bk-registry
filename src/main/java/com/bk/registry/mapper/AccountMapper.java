package com.bk.registry.mapper;


import com.bk.registry.api.enums.account.TypeAccount;
import com.bk.registry.domain.entity.account.Account;
import com.bk.registry.domain.enums.account.StatusAccount;
import com.bk.registry.mapper.dto.account.AccountRequestDTO;
import com.bk.registry.mapper.dto.account.AccountRequestUpdateDTO;
import com.bk.registry.mapper.dto.account.AccountResponseDTO;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AccountMapper {

    AccountRequestDTO accountDomainToDto(Account account);
    Account accountDtoToDomain(AccountRequestDTO accountRequestDTO);
    List<AccountResponseDTO> listAccountDomainToResponseDto(List<Account> accountList);
    AccountResponseDTO accountDomainToResponseDto(Account account);
    Account accountRequestUpdateToDomain(AccountRequestUpdateDTO accountRequestUpdateDTO);

    void copyAccountDtoUpdateToDomain(AccountRequestUpdateDTO accountRequestUpdateDTO, @MappingTarget Account account);
}

package com.bk.registry.mapper;


import com.bk.registry.mapper.dto.AccountRequestDTO;
import com.bk.registry.mapper.dto.AccountRequestUpdateDTO;
import com.bk.registry.mapper.dto.AccountResponseDTO;
import com.bk.registry.domain.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AccountMapper {

    AccountRequestDTO accountDomainToDto(Account account);
    Account accountDtoToDomain(AccountRequestDTO accountRequestDTO);
    List<AccountResponseDTO> listAccountDomainToResponseDto(List<Account> accountList);
    AccountResponseDTO accountDomainToResponseDto(Account account);

    void accountDtoUpdateToDomain(AccountRequestUpdateDTO accountRequestUpdateDTO, @MappingTarget Account account);
}

package com.bk.registry.domain.services.account;

import com.bk.registry.api.enums.account.StatusAccountApi;
import com.bk.registry.domain.entity.account.Account;
import com.bk.registry.mapper.dto.account.AccountRequestDTO;
import com.bk.registry.mapper.dto.account.AccountRequestUpdateDTO;
import com.bk.registry.mapper.dto.account.AccountResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountService {

    List<AccountResponseDTO> getAccounts();
    AccountResponseDTO saveAccount(AccountRequestDTO accountRequestDTO);
    AccountResponseDTO getAccountByBranchAndAccountNumber(Integer branch, Long account);
    void updateStatusAccount(StatusAccountApi status, UUID id);
    AccountResponseDTO updateAccount(UUID id, AccountRequestUpdateDTO accountRequestUpdateDTO);
    Account findAccountBydId(UUID id);
}

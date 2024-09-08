package com.bk.registry.domain.services;

import com.bk.registry.api.enums.StatusAccountApi;
import com.bk.registry.domain.domain.Account;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountService {

    List<Account> getAccounts();
    Account saveAccount(Account account);
    Optional<Account> getAccountByBranchAndAccountNumber(Integer branch, Long account);
    void updateStatusAccount(StatusAccountApi status, UUID id);
    Account updateAccount(Account account);
    Optional<Account> findAccountBydId(UUID id);
}

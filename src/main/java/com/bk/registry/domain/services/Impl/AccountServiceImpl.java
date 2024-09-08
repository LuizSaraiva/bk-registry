package com.bk.registry.domain.services.Impl;

import com.bk.registry.api.enums.StatusAccountApi;
import com.bk.registry.api.mapper.StatusAccountMapper;
import com.bk.registry.domain.domain.Account;
import com.bk.registry.domain.enums.StatusAccount;
import com.bk.registry.domain.exceptions.AccountAlreadyExistsException;
import com.bk.registry.domain.exceptions.AccountNotFoundException;
import com.bk.registry.domain.exceptions.enums.ExceptionMessage;
import com.bk.registry.domain.repositories.AccountRepository;
import com.bk.registry.domain.services.AccountService;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private StatusAccountMapper statusAccountMapper;

    @Value(value = "${registry.account.default-status-create}")
    private StatusAccount defaultStatusAccount;

    @Override
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @Override
    @Transactional
    public Account saveAccount(Account account) {

        if (checkBranchAccount(account) || checkDocument(account)) {
            throw new AccountAlreadyExistsException(ExceptionMessage.ACCOUNT_ALREADY_EXISTS.getMessage());
        }

        account.setCreate_date(OffsetDateTime.now());
        account.setStatus(defaultStatusAccount);
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> getAccountByBranchAndAccountNumber(Integer branch, Long account) {

        return Optional.ofNullable(accountRepository.findByBranchAndAccount(branch, account).orElseThrow(() ->
                new AccountNotFoundException(ExceptionMessage.ACCOUNT_NOT_FOUND.getMessage())));
    }

    @Override
    @Transactional
    public void updateStatusAccount(StatusAccountApi status, UUID id) {
        Optional<Account> accountFound = findAccountBydId(id);

        StatusAccount statusDomain = statusAccountMapper.statusApiToDomain(status);
        accountFound.get().setStatus(statusDomain);
        Account accountSaved = accountRepository.save(accountFound.get());
        System.out.println(accountSaved);
    }

    @Override
    @Transactional
    public Account updateAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> findAccountBydId(UUID id) {
        return Optional.ofNullable(accountRepository.findById(id).orElseThrow(
                () -> new AccountNotFoundException(id)
        ));
    }

    public boolean checkBranchAccount(Account account) {
        return accountRepository.findByBranchAndAccount(account.getBranch(), account.getAccount()).isPresent();
    }

    public boolean checkDocument(Account account) {
        return accountRepository.findByDocument(account.getDocument()).isPresent();
    }
}

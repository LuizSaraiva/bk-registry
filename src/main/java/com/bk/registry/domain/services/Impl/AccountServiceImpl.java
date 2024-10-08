package com.bk.registry.domain.services.Impl;

import com.bk.registry.api.enums.StatusAccountApi;
import com.bk.registry.convert.JsonConverter;
import com.bk.registry.domain.entity.Account;
import com.bk.registry.domain.entity.OutboxRegistry;
import com.bk.registry.domain.enums.StatusAccount;
import com.bk.registry.domain.enums.TypeEvent;
import com.bk.registry.domain.enums.TypeOutbox;
import com.bk.registry.domain.exceptions.AccountAlreadyExistsException;
import com.bk.registry.domain.exceptions.AccountNotFoundException;
import com.bk.registry.domain.exceptions.enums.ExceptionMessage;
import com.bk.registry.domain.repositories.AccountRepository;
import com.bk.registry.domain.services.AccountService;
import com.bk.registry.domain.services.OutboxRegistryService;
import com.bk.registry.mapper.AccountMessageMapper;
import com.bk.registry.mapper.OutboxMapper;
import com.bk.registry.mapper.StatusAccountMapper;
import com.bk.registry.mapper.dto.messaging.AccountMessageDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import lombok.val;
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
    private OutboxMapper outboxMapper;

    @Autowired
    private AccountMessageMapper accountMessageMapper;

    @Autowired
    JsonConverter jsonConverter;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private StatusAccountMapper statusAccountMapper;

    @Autowired
    OutboxRegistryService outboxRegistryService;

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

        final var accountSaved = setConfigsAndSaveAccount(account);

        OutboxRegistry outboxRegistry = createOutbox(accountSaved, TypeEvent.CREATE);

        outboxRegistryService.saveOutbox(outboxRegistry);
        return accountSaved;
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

    private OutboxRegistry createOutbox(Account account, TypeEvent typeEvent) {

        AccountMessageDTO accountMessageDTO = accountMessageMapper.accountDomainToMessage(account);

        OutboxRegistry outboxRegistry = outboxMapper.accountDomainToOutbox(accountMessageDTO);
        outboxRegistry.setType(TypeOutbox.ACCOUNT);
        outboxRegistry.setUpdate_date(OffsetDateTime.now());
        outboxRegistry.setCreate_date(OffsetDateTime.now());
        outboxRegistry.setTypeEvent(typeEvent);

        try {
            String json = jsonConverter.toJson(accountMessageDTO);
            outboxRegistry.setMessage(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return outboxRegistry;
    }

    private Account setConfigsAndSaveAccount(Account account) {
        account.setCreate_date(OffsetDateTime.now());
        account.setStatus(defaultStatusAccount);
        val accountSaved = accountRepository.save(account);
        return accountSaved;
    }
}

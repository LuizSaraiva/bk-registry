package com.bk.registry.domain.services.account.Impl;

import com.bk.registry.api.enums.account.StatusAccountApi;
import com.bk.registry.convert.JsonConverter;
import com.bk.registry.domain.entity.account.Account;
import com.bk.registry.domain.entity.account.OutboxRegistry;
import com.bk.registry.domain.enums.TypeEvent;
import com.bk.registry.domain.enums.TypeOutbox;
import com.bk.registry.domain.enums.account.StatusAccount;
import com.bk.registry.domain.exceptions.account.AccountAlreadyExistsException;
import com.bk.registry.domain.exceptions.account.AccountNotFoundException;
import com.bk.registry.domain.exceptions.account.enums.AccountExceptionMessage;
import com.bk.registry.domain.repositories.account.AccountRepository;
import com.bk.registry.domain.services.OutboxRegistryService;
import com.bk.registry.domain.services.account.AccountService;
import com.bk.registry.mapper.AccountMapper;
import com.bk.registry.mapper.AccountMessageMapper;
import com.bk.registry.mapper.OutboxMapper;
import com.bk.registry.mapper.StatusAccountMapper;
import com.bk.registry.mapper.dto.account.AccountRequestDTO;
import com.bk.registry.mapper.dto.account.AccountRequestUpdateDTO;
import com.bk.registry.mapper.dto.account.AccountResponseDTO;
import com.bk.registry.mapper.dto.account.messaging.AccountMessageDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class AccountServiceImpl implements AccountService {

    private final OutboxMapper outboxMapper;
    private final AccountMessageMapper accountMessageMapper;
    private final JsonConverter jsonConverter;
    private final AccountRepository accountRepository;
    private final StatusAccountMapper statusAccountMapper;
    private final OutboxRegistryService outboxRegistryService;
    private final AccountMapper accountMapper;

    @Value(value = "${registry.account.default-status-create}")
    private StatusAccount defaultStatusAccount;

    public AccountServiceImpl(OutboxMapper outboxMapper, AccountMessageMapper accountMessageMapper, JsonConverter jsonConverter, AccountRepository accountRepository, StatusAccountMapper statusAccountMapper, OutboxRegistryService outboxRegistryService, AccountMapper accountMapper) {
        this.outboxMapper = outboxMapper;
        this.accountMessageMapper = accountMessageMapper;
        this.jsonConverter = jsonConverter;
        this.accountRepository = accountRepository;
        this.statusAccountMapper = statusAccountMapper;
        this.outboxRegistryService = outboxRegistryService;
        this.accountMapper = accountMapper;
    }

    @Override
    public List<AccountResponseDTO> getAccounts() {
        return accountMapper.listAccountDomainToResponseDto(accountRepository.findAll());
    }

    @Override
    @Transactional
    public AccountResponseDTO saveAccount(AccountRequestDTO accountRequestDTO) {

        Account account = accountMapper.accountDtoToDomain(accountRequestDTO);

        if (checkBranchAccount(account) || checkDocument(account)) {
            throw new AccountAlreadyExistsException(AccountExceptionMessage.ACCOUNT_ALREADY_EXISTS.getMessage());
        }

        final var accountSaved = setConfigsAndSaveAccount(account);

        saveOutbox(accountSaved, TypeEvent.CREATE);

        return accountMapper.accountDomainToResponseDto(accountSaved);
    }

    private void saveOutbox(Account accountSaved, TypeEvent typeEvent) {
        OutboxRegistry outboxRegistry = createOutbox(accountSaved, typeEvent);
        outboxRegistryService.saveOutbox(outboxRegistry);
    }

    @Override
    public AccountResponseDTO getAccountByBranchAndAccountNumber(Integer branch, Long account) {

        Account accountFound = accountRepository.findByBranchAndAccount(branch, account).orElseThrow(
                () -> new AccountNotFoundException(branch, account));

        return accountMapper.accountDomainToResponseDto(accountFound);
    }

    @Override
    @Transactional
    public void updateStatusAccount(StatusAccountApi status, UUID id) {
        Account accountFound = findAccountBydId(id);

        StatusAccount statusDomain = statusAccountMapper.statusApiToDomain(status);
        accountFound.setStatus(statusDomain);
        Account accountSaved = accountRepository.save(accountFound);
        saveOutbox(accountSaved, TypeEvent.UPDATE);
    }

    @Override
    @Transactional
    public AccountResponseDTO updateAccount(UUID id, AccountRequestUpdateDTO accountRequestUpdateDTO) {

        Account accountFound = findAccountBydId(id);

        accountMapper.copyAccountDtoUpdateToDomain(accountRequestUpdateDTO, accountFound);
        Account accountSaved = accountRepository.save(accountFound);
        saveOutbox(accountSaved, TypeEvent.UPDATE);
        return accountMapper.accountDomainToResponseDto(accountSaved);
    }

    @Override
    public Account findAccountBydId(UUID id) {
        return accountRepository.findById(id).orElseThrow(
                () -> new AccountNotFoundException(id));
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
        return accountRepository.save(account);
    }
}

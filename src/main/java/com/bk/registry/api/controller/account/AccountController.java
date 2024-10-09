package com.bk.registry.api.controller.account;

import com.bk.registry.domain.exceptions.account.AccountAlreadyExistsException;
import com.bk.registry.domain.exceptions.account.AccountNotFoundException;
import com.bk.registry.domain.services.account.AccountService;
import com.bk.registry.mapper.StatusAccountMapper;
import com.bk.registry.mapper.dto.account.AccountRequestDTO;
import com.bk.registry.mapper.dto.account.AccountRequestUpdateDTO;
import com.bk.registry.mapper.dto.account.AccountResponseDTO;
import com.bk.registry.mapper.dto.account.UpdateStatusDTO;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/account")
@Log4j2
public class AccountController implements AccountControllerApi {

    private final AccountService accountService;
    private final StatusAccountMapper statusAccountMapper;

    public AccountController(AccountService accountService, StatusAccountMapper statusAccountMapper) {
        this.accountService = accountService;
        this.statusAccountMapper = statusAccountMapper;
    }

    @Override
    public ResponseEntity<?> getAccounts() {
        log.info("Received request to get all accounts.");
        List<AccountResponseDTO> accountResponseDTOList = accountService.getAccounts();
        log.info("Response request to get all accounts.");
        return ResponseEntity.ok(accountResponseDTOList);
    }

    @Override
    public ResponseEntity<?> getAccount(Integer branch, Long account) {
        log.info("Received request to get account.");
        AccountResponseDTO accountResponseDTO = accountService.getAccountByBranchAndAccountNumber(branch, account);
        log.info("Response request to get account.");
        return ResponseEntity.ok().body(accountResponseDTO);
    }

    @Override
    public ResponseEntity<?> createAccount(@Valid AccountRequestDTO accountRequestDTO) {
        log.info("Received request to create account: {}", accountRequestDTO);
        AccountResponseDTO accountSaved = accountService.saveAccount(accountRequestDTO);
        log.info("Response account created successfully: {}", accountSaved);
        return ResponseEntity.status(HttpStatus.CREATED).body(accountSaved);
    }

    @Override
    public ResponseEntity<?> updateAccount(UUID id, AccountRequestUpdateDTO accountRequestUpdateDTO) {
        log.info("Received request to update account: {}", id);
        AccountResponseDTO accountSaved = accountService.updateAccount(id, accountRequestUpdateDTO);
        log.info("Response request to update account {}: {}", id, accountSaved);
        return ResponseEntity.ok(accountSaved);
    }

    @Override
    public ResponseEntity<?> updateStatusAccount(UUID id, @Valid UpdateStatusDTO updateStatusDTO) {
        log.info("Received request to update status account: {}", id);
        accountService.updateStatusAccount(updateStatusDTO.getStatus(), id);
        log.info("Response request to update status account {} : updated!", id);
        return ResponseEntity.ok().build();
    }
}

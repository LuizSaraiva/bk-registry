package com.bk.registry.api.controller;

import com.bk.registry.domain.entity.Account;
import com.bk.registry.domain.exceptions.AccountAlreadyExistsException;
import com.bk.registry.domain.services.AccountService;
import com.bk.registry.mapper.AccountMapper;
import com.bk.registry.mapper.StatusAccountMapper;
import com.bk.registry.mapper.dto.AccountRequestDTO;
import com.bk.registry.mapper.dto.AccountRequestUpdateDTO;
import com.bk.registry.mapper.dto.AccountResponseDTO;
import com.bk.registry.mapper.dto.UpdateStatusDTO;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/account")
@Log4j2
public class AccountController implements AccountControllerApi {

    @Autowired
    private AccountService accountService;

    @Autowired
    AccountMapper accountMapper;

    @Autowired
    StatusAccountMapper statusAccountMapper;

    @Override
    public ResponseEntity<?> getAccounts() {

        log.info("Received request to get all accounts.");
        val accountResponses = accountMapper.listAccountDomainToResponseDto(accountService.getAccounts());
        log.info("Response request to get all accounts.");
        return ResponseEntity.ok(accountResponses);
    }

    @Override
    public ResponseEntity<?> getAccount(Integer branch, Long account) {
        log.info("Received request to get account.");

        val accountByBranchAndAccountNumber = accountService.getAccountByBranchAndAccountNumber(branch, account);

        if (!accountByBranchAndAccountNumber.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found.");
        }
        val accountResponses = accountMapper.accountDomainToResponseDto(accountByBranchAndAccountNumber.get());
        log.info("Response request to get account.");
        return ResponseEntity.ok(accountResponses);

    }

    @Override
    public ResponseEntity<?> createAccount(@Valid AccountRequestDTO accountRequestDTO) {

        log.info("Received request to create account: {}", accountRequestDTO);
        Account account = accountMapper.accountDtoToDomain(accountRequestDTO);
        try{
            Account accountSaved = accountService.saveAccount(account);
            AccountResponseDTO accountResponse = accountMapper.accountDomainToResponseDto(accountSaved);
            log.info("Response account created successfully: {}", accountResponse);
            return ResponseEntity.status(HttpStatus.CREATED).body(accountResponse);
        }catch (AccountAlreadyExistsException ex){
            log.error(ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateAccount(UUID id, AccountRequestUpdateDTO accountRequestUpdateDTO) {

        Optional<Account> accountFound = accountService.findAccountBydId(id);

        if(!accountFound.isPresent()){
            return ResponseEntity.notFound().build();
        }
        accountMapper.accountDtoUpdateToDomain(accountRequestUpdateDTO, accountFound.get());

        val accountSaved = accountService.updateAccount(accountFound.get());

        return ResponseEntity.ok(accountSaved);
    }

    @Override
    public ResponseEntity<?> updateStatusAccount(UUID id, @Valid UpdateStatusDTO updateStatusDTO) {
        try{
            accountService.updateStatusAccount(updateStatusDTO.getStatus(),id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().body("");
        }
    }
}

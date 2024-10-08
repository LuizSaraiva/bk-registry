package com.bk.registry.api.controller.account;

import com.bk.registry.mapper.dto.account.AccountRequestDTO;
import com.bk.registry.mapper.dto.account.AccountRequestUpdateDTO;
import com.bk.registry.mapper.dto.account.UpdateStatusDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public interface AccountControllerApi {

    @GetMapping
    ResponseEntity<?> getAccounts();

    @GetMapping("/{branch}/{account}")
    ResponseEntity<?> getAccount(@PathVariable("branch") Integer branch, @PathVariable("account") Long account);

    @PostMapping
    ResponseEntity<?> createAccount(@RequestBody AccountRequestDTO accountRequestDTO);

    @PutMapping("/{id}")
    ResponseEntity<?> updateAccount(@PathVariable("id") UUID id,
                                    @RequestBody AccountRequestUpdateDTO accountRequestUpdateDTO);

    @PatchMapping("/{id}")
    ResponseEntity<?> updateStatusAccount(@PathVariable("id") UUID id,
                                          @RequestBody UpdateStatusDTO updateStatusDTO);

}

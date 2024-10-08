package com.bk.registry.domain.repositories.account;

import com.bk.registry.domain.entity.account.Account;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends AccountCustomRepository<Account, UUID>{

    Optional<Account> findByBranchAndAccount(Integer branch, Long account);

    Optional<Account> findByDocument(String document);

}

package com.bk.registry.domain.repositories;

import com.bk.registry.domain.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@NoRepositoryBean
public interface AccountCustomRepository<T, ID> extends JpaRepository<T, ID> {
}

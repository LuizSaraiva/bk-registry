package com.bk.registry.domain.repositories.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AccountCustomRepository<T, ID> extends JpaRepository<T, ID> {
}

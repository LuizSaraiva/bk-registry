package com.bk.registry.domain.repositories.historytransaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface HistoryTransactionCustomRepository<T, ID> extends JpaRepository<T, ID> {
}

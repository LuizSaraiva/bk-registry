package com.bk.registry.domain.repositories.historytransaction;

import com.bk.registry.domain.entity.historytransaction.HistoryTransaction;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HistoryTransactionRepository extends HistoryTransactionCustomRepository<HistoryTransaction, UUID> {

}

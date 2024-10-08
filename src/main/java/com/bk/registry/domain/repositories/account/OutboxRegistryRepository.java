package com.bk.registry.domain.repositories.account;

import com.bk.registry.domain.entity.account.OutboxRegistry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OutboxRegistryRepository extends JpaRepository<OutboxRegistry, UUID> {

    List<OutboxRegistry> findAllBySentFalse();
}

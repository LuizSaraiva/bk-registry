package com.bk.registry.domain.repositories;

import com.bk.registry.domain.entity.account.OutboxRegistry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OutboxRegistryRepository extends JpaRepository<OutboxRegistry, UUID> {

    @Query(value = "SELECT * FROM tb_outbox_registry tor WHERE sent = 0 order by update_date LIMIT :limit", nativeQuery = true)
    List<OutboxRegistry> findAllBySentFalse(@Param("limit") int limit);
}

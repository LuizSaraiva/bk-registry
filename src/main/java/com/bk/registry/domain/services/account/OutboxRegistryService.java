package com.bk.registry.domain.services.account;

import com.bk.registry.domain.entity.account.OutboxRegistry;

import java.util.List;

public interface OutboxRegistryService {

    OutboxRegistry saveOutbox(OutboxRegistry outboxRegistry);
    List<OutboxRegistry> getOutboxNotSent();
}

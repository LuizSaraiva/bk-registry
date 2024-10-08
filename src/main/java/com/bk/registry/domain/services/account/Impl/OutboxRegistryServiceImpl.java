package com.bk.registry.domain.services.account.Impl;

import com.bk.registry.domain.entity.account.OutboxRegistry;
import com.bk.registry.domain.repositories.account.OutboxRegistryRepository;
import com.bk.registry.domain.services.account.OutboxRegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutboxRegistryServiceImpl implements OutboxRegistryService {

    @Autowired
    private OutboxRegistryRepository outboxRegistryRepository;

    @Override
    public OutboxRegistry saveOutbox(OutboxRegistry outboxRegistry) {
        return outboxRegistryRepository.save(outboxRegistry);
    }

    @Override
    public List<OutboxRegistry> getOutboxNotSent() {
        return outboxRegistryRepository.findAllBySentFalse();
    }
}

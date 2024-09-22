package com.bk.registry.domain.services.Impl;

import com.bk.registry.domain.entity.OutboxRegistry;
import com.bk.registry.domain.repositories.OutboxRegistryRepository;
import com.bk.registry.domain.services.OutboxRegistryService;
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

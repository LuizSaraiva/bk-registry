package com.bk.registry.domain.services;

import com.bk.registry.domain.entity.account.OutboxRegistry;
import com.bk.registry.domain.repositories.OutboxRegistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutboxRegistryServiceImpl implements OutboxRegistryService {

    @Autowired
    private OutboxRegistryRepository outboxRegistryRepository;

    @Value(value = "${registry.outbox.limit-send-to-broker:100}")
    private int limitSendToBroker;

    @Override
    public OutboxRegistry saveOutbox(OutboxRegistry outboxRegistry) {
        return outboxRegistryRepository.save(outboxRegistry);
    }

    @Override
    public List<OutboxRegistry> getOutboxNotSent() {
        return outboxRegistryRepository.findAllBySentFalse(limitSendToBroker);
    }
}

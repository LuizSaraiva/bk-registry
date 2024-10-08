package com.bk.registry.schedulers;

import com.bk.registry.domain.entity.account.OutboxRegistry;
import com.bk.registry.domain.enums.TypeOutbox;
import com.bk.registry.domain.publishers.EventPublisher;
import com.bk.registry.domain.services.account.OutboxRegistryService;
import com.bk.registry.mapper.AccountMessageMapper;
import com.bk.registry.mapper.dto.account.messaging.MessageDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@Log4j2
public class SendMessageToBroker {

    @Value(value = "${rabbitmq.broker.routing.account}")
    private String routingAccount;

    @Value(value = "${rabbitmq.broker.exchange}")
    private String exchangeRegistry;

    @Autowired
    private EventPublisher eventPublisher;

    @Autowired
    private OutboxRegistryService outboxRegistryService;

    @Autowired
    private AccountMessageMapper messageMapper;

    @Scheduled(initialDelay = 10, fixedDelay = 20, timeUnit = TimeUnit.SECONDS)
    public void scheduleOutbox() {

        List<OutboxRegistry> outboxNotSent = outboxRegistryService.getOutboxNotSent();
        log.info(String.format("%s messages found to be sent to the broker", outboxNotSent.size()));

        try {
            outboxNotSent.forEach(outbox -> {setRoutingKeyAndSendMessage(outbox);});
        } catch (RuntimeException ex) {
            log.error(ex);
        }

    }

    private void setRoutingKeyAndSendMessage(OutboxRegistry outboxRegistry) {
        TypeOutbox outboxRegistryType = outboxRegistry.getType();

        MessageDTO messageDTO = messageMapper.accountMessageDTOToMessageBroker(outboxRegistry);

        switch (outboxRegistryType) {
            case ACCOUNT -> {
                eventPublisher.publisherEvent(exchangeRegistry, routingAccount, messageDTO);
                outboxRegistry.setSent(true);
                outboxRegistry.setUpdate_date(OffsetDateTime.now());
                outboxRegistryService.saveOutbox(outboxRegistry);
            }
            default -> log.error("Routing not found!");
        }
    }
}

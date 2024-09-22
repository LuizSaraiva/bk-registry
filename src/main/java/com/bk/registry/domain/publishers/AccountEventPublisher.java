package com.bk.registry.domain.publishers;

import com.bk.registry.domain.entity.Account;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class AccountEventPublisher {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Value(value = "${rabbitmq.broker.exchange}")
    private String exchangeRegistry;

    @Value(value = "${rabbitmq.broker.routing.account}")
    private String routingAccount;

    public void publisherAccountEvent(Account account){
        rabbitTemplate.convertAndSend(exchangeRegistry, routingAccount, account);
        log.debug("Message send to broker.");
    }

}

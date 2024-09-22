package com.bk.registry.domain.publishers;

import com.bk.registry.domain.entity.OutboxRegistry;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class EventPublisher{

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void publisherEvent(String exchangeRegistry, String routingAccount, String message){
        rabbitTemplate.convertAndSend(exchangeRegistry, routingAccount, message);
        log.info(String.format("Sending Message: %s", message));
    }

}

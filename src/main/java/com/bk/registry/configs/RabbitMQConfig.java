package com.bk.registry.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;

    @Value(value = "${rabbitmq.broker.queue.account}")
    private String queueRegistryAccountName;

    @Value(value = "${rabbitmq.broker.queue.history-transaction}")
    private String queueRegistryHistoryTransactionName;

    @Value(value = "${rabbitmq.broker.exchange}")
    private String exchangeRegistry;

    @Value(value = "${rabbitmq.broker.routing.account}")
    private String routingAccount;

    @Value(value = "${rabbitmq.broker.routing.history-transaction}")
    private String routingHistoryTransaction;


    @Bean
    public Queue queueRegistryAccount(){
        return new Queue(queueRegistryAccountName,true);
    }

    @Bean
    public Queue queueRegistryHistoryTransaction(){
        return new Queue(queueRegistryHistoryTransactionName,true);
    }


    @Bean
    public TopicExchange topicExchangeRegistryAccount(){
        return new TopicExchange(exchangeRegistry);
    }

    @Bean
    public Binding bindingRegistryAccount(){
        return BindingBuilder.bind(queueRegistryAccount())
                .to(topicExchangeRegistryAccount())
                .with(routingAccount);
    }

    @Bean
    public Binding bindingRegistryHistoryTransaction(){
        return BindingBuilder.bind(queueRegistryHistoryTransaction())
                .to(topicExchangeRegistryAccount())
                .with(routingHistoryTransaction);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate template = new RabbitTemplate(cachingConnectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(objectMapper);
    }

}

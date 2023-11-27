package com.example.reservas.configuration;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.*;

@Configuration
public class RabbitMQConfig {
    @Bean
    public DirectExchange parkingExchange() {
        return new DirectExchange("parkingExchange");
    }

    @Bean
    public FanoutExchange parkingFanoutExchange() {
        return new FanoutExchange("parkingFanoutExchange");
    }

    @Bean
    public Queue parkingCola() {
        return QueueBuilder.durable("parkingCola").build();
    }

    @Bean
    public Queue parkingCola1() {
        return QueueBuilder.durable("parkingCola1").build();
    }

    @Bean
    public Queue parkingCola2() {
        return QueueBuilder.durable("parkingCola2").build();
    }

    @Bean
    public Queue parkingColaFO() {
        return QueueBuilder.durable("parkingColaFO").build();
    }

    @Bean
    public Queue parkingCola1FO() {
        return QueueBuilder.durable("parkingCola1FO").build();
    }

    @Bean
    public Binding parkingColaBinding(DirectExchange parkingExchange, Queue parkingCola) {
        return BindingBuilder.bind(parkingCola).to(parkingExchange).with("parkingRoutingKey");
    }

    @Bean
    public Binding parkingCola1Binding(DirectExchange parkingExchange, Queue parkingCola1) {
        return BindingBuilder.bind(parkingCola1).to(parkingExchange).with("parkingRoutingKey");
    }

    @Bean
    public Binding parkingCola2Binding(DirectExchange parkingExchange, Queue parkingCola2) {
        return BindingBuilder.bind(parkingCola2).to(parkingExchange).with("parkingRoutingKey2");
    }

    @Bean
    public Binding parkingColaFOBinding(FanoutExchange parkingFanoutExchange, Queue parkingColaFO) {
        return BindingBuilder.bind(parkingColaFO).to(parkingFanoutExchange);
    }

    @Bean
    public Binding parkingCola1FOBinding(FanoutExchange parkingFanoutExchange, Queue parkingCola1FO) {
        return BindingBuilder.bind(parkingCola1FO).to(parkingFanoutExchange);
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate amqpTemplate(org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
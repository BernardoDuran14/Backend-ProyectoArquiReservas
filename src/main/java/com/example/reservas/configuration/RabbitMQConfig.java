package com.example.reservas.configuration;

//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitMQConfig {
//
//    @Bean
//    public DirectExchange gameReserve(){
//        return new DirectExchange("test-reserve");
//    }
//
//    @Bean
//    public Queue gameReserveQueue1(){
//        return QueueBuilder.durable("test-queue1").build();
//    }
//
//    @Bean
//    public Queue gameReserveQueue2(){
//        return QueueBuilder.durable("test-queue2").build();
//    }
//
//    @Bean
//    public Queue reportQueue(){
//        return QueueBuilder.durable("test-report").build();
//    }
//
//    @Bean
//    public Binding reserveBinding1(DirectExchange gameReserve, Queue gameReserveQueue1){
//        return BindingBuilder
//                .bind(gameReserveQueue1)
//                .to(gameReserve)
//                .with("reserver.routingkey");
//    }
//
//    @Bean
//    public Binding reserveBinding2(DirectExchange gameReserve, Queue gameReserveQueue2){
//        return BindingBuilder
//                .bind(gameReserveQueue2)
//                .to(gameReserve)
//                .with("reserver.routingkey");
//    }
//
//    @Bean
//    public Binding reportBinding(DirectExchange gameReserve, Queue reportQueue){
//        return BindingBuilder
//                .bind(reportQueue)
//                .to(gameReserve)
//                .with("report.routingkey");
//    }
//
//    @Bean
//    public Jackson2JsonMessageConverter converter(){
//        return new Jackson2JsonMessageConverter();
//    }
//
//    @Bean
//    public AmqpTemplate template(ConnectionFactory connectionFactory){
//        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(converter());
//        return rabbitTemplate;
//    }
//
//
//}

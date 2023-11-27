package com.example.reservas.producer;

import com.example.reservas.dto.NotificationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.amqp.core.AmqpTemplate;


@Service
@Slf4j
public class NotificationProducer {

    @Autowired
    private AmqpTemplate ampqTemplate;

    public String sendNotification(NotificationDto notificationDto, String routingKey) {
        log.info("Sending message...");
        ampqTemplate.convertAndSend("parking.exchange", routingKey, notificationDto);
        return "Notification sent successfully";
    }


}

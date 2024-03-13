package org.microservices.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.microservices.event.OrderPlacedEvent;
import org.microservices.service.NotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


@Controller
@AllArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @KafkaListener(topics = "notificationTopic")
    public void handleOrderPlacedEvent(OrderPlacedEvent order) {
        notificationService.sendEmail(order);
    }
}

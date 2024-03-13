package org.microservices.service;

import lombok.AllArgsConstructor;
import org.microservices.event.EmailDetails;
import org.microservices.event.OrderPlacedEvent;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationService {
    private final EmailService emailService;

    public void sendEmail(OrderPlacedEvent order) {
        emailService.sendEmail(EmailDetails.builder()
                .messageBody("Registration Successful order number: "+ order.getOrderNumber())
                .recipient(order.getCustomerMail())
                .subject("REGISTRATION SUCCESS")
                .build());
    }
}

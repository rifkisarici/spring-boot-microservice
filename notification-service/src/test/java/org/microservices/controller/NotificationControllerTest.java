package org.microservices.controller;

import org.junit.jupiter.api.Test;
import org.microservices.event.OrderPlacedEvent;
import org.microservices.service.NotificationService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class NotificationControllerTest {
    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private NotificationController notificationController;

    @Test
    void handleOrderPlacedEvent(){
        OrderPlacedEvent order=new OrderPlacedEvent("orderNumber","customerMail");

        // When
        notificationController.handleOrderPlacedEvent(order);

        // Then
        verify(notificationService, times(1)).sendEmail(order);
    }
}

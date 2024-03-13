package microservice.orderservice.service;

import microservice.orderservice.Proxy.InventoryServiceProxy_WebFlux;
import microservice.orderservice.dto.InventoryResponse;
import microservice.orderservice.dto.OrderLineItemsDTO;
import microservice.orderservice.dto.OrderRequest;
import microservice.orderservice.event.OrderPlacedEvent;
import microservice.orderservice.model.Order;
import microservice.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class OrderServiceTest {
    @InjectMocks
    private OrderService orderService;
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    @Mock
    private InventoryServiceProxy_WebFlux inventoryServiceProxyWebFlux;

    @Test
    void testPlaceOrder_Successful() {
        // Arrange
        OrderLineItemsDTO orderLineItemsDTO=new OrderLineItemsDTO();
        orderLineItemsDTO.setPrice(BigDecimal.valueOf(1500));
        orderLineItemsDTO.setSkuCode("SKU123");
        orderLineItemsDTO.setQuantity(1);
        orderLineItemsDTO.setId(1L);

        List<OrderLineItemsDTO> orderLineItemsDTOList =new ArrayList<>();
        orderLineItemsDTOList.add(orderLineItemsDTO);

        OrderRequest orderRequest=new OrderRequest();
        orderRequest.setOrderLineItemsDTOList(orderLineItemsDTOList);

       /* List<String> skuCodes = new ArrayList<>();
        skuCodes.add(orderLineItemsDTO.getSkuCode());*/

        InventoryResponse[] inventoryResponse= new InventoryResponse[]{
                new InventoryResponse(orderLineItemsDTO.getSkuCode(), true)
        };

        // Mocking inventory service response
        when(inventoryServiceProxyWebFlux.isStock(List.of(orderLineItemsDTO.getSkuCode()))).thenReturn(inventoryResponse);

        // Act
        String response = orderService.placeOrder(orderRequest);

        // Assert
        assertEquals("successful", response); // Assuming it returns "successful" when any product is in stock
        verify(orderRepository, times(1)).save(any(Order.class));// Ensure that order is saved
        verify(kafkaTemplate, times(1)).send(eq("notificationTopic"), any(OrderPlacedEvent.class)); // Ensure that notification is sent
    }

    private OrderRequest createOrder(){
        OrderRequest orderRequest = new OrderRequest();
        OrderLineItemsDTO orderLineItemsDTO = new OrderLineItemsDTO();
        orderLineItemsDTO.setSkuCode("SKU123");
        orderRequest.setOrderLineItemsDTOList(Collections.singletonList(orderLineItemsDTO));

        InventoryResponse[] inventoryResponses = {new InventoryResponse("SKU123", true)};
        when(inventoryServiceProxyWebFlux.isStock(anyList())).thenReturn(inventoryResponses);

        return orderRequest;
    }
}

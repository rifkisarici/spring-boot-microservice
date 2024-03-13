package microservice.orderservice.controller;

import microservice.orderservice.dto.OrderLineItemsDTO;
import microservice.orderservice.dto.OrderRequest;
import microservice.orderservice.service.OrderService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.annotation.Id;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class OrderControllerTest {
    @Mock
    private OrderService orderService;
    @InjectMocks
    private OrderController orderController;

    @Test
    void placeOrder() {
        OrderLineItemsDTO orderLineItemsDTO=new OrderLineItemsDTO();
        orderLineItemsDTO.setPrice(BigDecimal.valueOf(1500));
        orderLineItemsDTO.setSkuCode("nokia");
        orderLineItemsDTO.setQuantity(1);
        orderLineItemsDTO.setId(1L);

        List<OrderLineItemsDTO> orderLineItemsDTOList =new ArrayList<>();
        orderLineItemsDTOList.add(orderLineItemsDTO);

        OrderRequest orderRequest=new OrderRequest();
        orderRequest.setOrderLineItemsDTOList(orderLineItemsDTOList);

        String expectedResponse = "successful";
        when(orderService.placeOrder(orderRequest)).thenReturn(expectedResponse);

        // Act
        String actualResponse = orderController.placeOrder(orderRequest);

        // Assert
        assertEquals(expectedResponse, actualResponse);
    }

}

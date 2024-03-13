package microservice.orderservice.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import microservice.orderservice.Proxy.InventoryServiceProxy_OpenFeign;
import microservice.orderservice.Proxy.InventoryServiceProxy_WebFlux;
import microservice.orderservice.dto.InventoryResponse;
import microservice.orderservice.dto.OrderLineItemsDTO;
import microservice.orderservice.dto.OrderRequest;
import microservice.orderservice.event.OrderPlacedEvent;
import microservice.orderservice.model.Order;
import microservice.orderservice.model.OrderLineItems;
import microservice.orderservice.repository.OrderRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
@Data
@Transactional
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final InventoryServiceProxy_OpenFeign inventoryServiceProxyOpenFeign;
    private final InventoryServiceProxy_WebFlux inventoryServiceProxyWebFlux;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;
    public String placeOrder(OrderRequest orderRequest) {
        Order order=new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderLineItemsList(orderRequest.getOrderLineItemsDTOList().stream().map(this::toOrderLineItems).toList());
        order.setCustomerMail(orderRequest.getCustomerMail());
        List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

        //InventoryResponse[] inventoryResponseArray2= inventoryServiceProxyOpenFeign.isStock(skuCodes);
        InventoryResponse[] inventoryResponseArray= inventoryServiceProxyWebFlux.isStock(skuCodes);
        boolean allProductsInStock = Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::getIsInStock);
        if(allProductsInStock) {
            //orderRepository.save(order);
            kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getOrderNumber(),order.getCustomerMail()));
            log.info("Order {} is saved",order.getId());
            return "successful";
        }else {
            log.info("Order {} is not saved",order.getId());
            return "not successful";
        }

    }

    private OrderLineItems toOrderLineItems(OrderLineItemsDTO orderLineItemsDTO) {
        OrderLineItems orderLineItems=new OrderLineItems();
        orderLineItems.setSkuCode(orderLineItemsDTO.getSkuCode());
        orderLineItems.setQuantity(orderLineItemsDTO.getQuantity());
        orderLineItems.setPrice(orderLineItemsDTO.getPrice());
        return orderLineItems;
    }
}

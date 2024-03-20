package com.microservice.inventoryservice.service;

import com.microservice.inventoryservice.dto.InventoryResponse;
import com.microservice.inventoryservice.model.Inventory;
import com.microservice.inventoryservice.repository.InventoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class InventoryServiceTest {
    @Mock
    private InventoryRepository inventoryRepository;
    @InjectMocks
    private InventoryService inventoryService;

    @Test
    void isStock(){
        List<String> list=List.of("skuCode");

        Inventory inventory=new Inventory(1L,"skuCode",1);
        List<Inventory> expectedResponse =List.of(inventory);

        when(inventoryRepository.findBySkuCodeIn(list)).thenReturn(expectedResponse);

        InventoryResponse inventoryResponse=new InventoryResponse(inventory.getSkuCode(),inventory.getQuantity()>0);
        List<InventoryResponse> inventoryResponses=List.of(inventoryResponse);

        assertEquals(inventoryService.isStock(list),inventoryResponses);
        assertEquals (inventory.getId(),1L);

    }

}

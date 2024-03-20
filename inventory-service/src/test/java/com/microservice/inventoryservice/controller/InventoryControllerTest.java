package com.microservice.inventoryservice.controller;

import com.microservice.inventoryservice.dto.InventoryResponse;
import com.microservice.inventoryservice.service.InventoryService;
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
public class InventoryControllerTest {
    @Mock
    private InventoryService inventoryService;
    @InjectMocks
    private InventoryController inventoryController;

    @Test
    void isStock(){
        List<String> list=List.of("skuCode");

        InventoryResponse inventoryResponse=new InventoryResponse("skuCode",true);
        List<InventoryResponse> expectedResponse=List.of(inventoryResponse);

        when(inventoryService.isStock(list)).thenReturn(expectedResponse);

        List<InventoryResponse> actualResponse=inventoryController.isStock(list);

        // Assert
        assertEquals(expectedResponse, actualResponse);
    }
}

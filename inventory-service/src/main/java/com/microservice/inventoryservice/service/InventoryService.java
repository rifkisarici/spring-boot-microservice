package com.microservice.inventoryservice.service;

import com.microservice.inventoryservice.dto.InventoryResponse;
import com.microservice.inventoryservice.model.Inventory;
import com.microservice.inventoryservice.repository.InventoryRepository;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isStock(List<String> skuCode) {
        List<Inventory> inventoryList = inventoryRepository.findBySkuCodeIn(skuCode);
        return inventoryList.stream().map(i-> new InventoryResponse(i.getSkuCode(),i.getQuantity()>0)).toList();
    }
}

package com.microservice.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class InventoryResponse {
    private String skuCode;
    private Boolean isInStock;
}

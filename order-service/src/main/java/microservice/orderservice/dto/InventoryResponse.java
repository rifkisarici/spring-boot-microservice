package microservice.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class InventoryResponse {
    private String skuCode;
    private Boolean isInStock;
}

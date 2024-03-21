package com.microservice.productservice.application.command;

import com.microservice.productservice.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class UpdateProductCommand {
    private String id;
    private String name;
    private String description;
    private String price;
    public Product toDomainEntity() {
        return new Product(getId(), getName(), getDescription(), getPrice());
    }
}

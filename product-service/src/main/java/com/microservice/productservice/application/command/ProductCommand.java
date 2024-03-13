package com.microservice.productservice.application.command;

import com.microservice.productservice.domain.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductCommand {
    private String name;
    private String description;
    private String price;

    public Product toDomainEntity() {
        return new Product("-1", getName(),getDescription(),getPrice());
    }
}

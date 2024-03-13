package com.microservice.productservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Product {
    private String id;
    private String name;
    private String description;
    private String price;
}

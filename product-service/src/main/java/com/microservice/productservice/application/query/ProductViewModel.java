package com.microservice.productservice.application.query;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductViewModel {
    private String id;
    private String name;
    private String description;
    private String price;
}

package com.microservice.productservice.application.port;

import com.microservice.productservice.application.query.ProductViewModel;
import com.microservice.productservice.domain.Product;

import java.util.List;

public interface ProductPort {
    void createProduct(Product product);
    List<ProductViewModel> getAllProduct();

}

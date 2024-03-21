package com.microservice.productservice.application.port;

import com.microservice.productservice.application.query.ProductViewModel;
import com.microservice.productservice.domain.Product;

import java.util.List;

public interface ProductPort {
    ProductViewModel createProduct(Product product);
    List<ProductViewModel> getAllProduct();

    ProductViewModel updateProduct(Product product);

    List<ProductViewModel> getAllProductByName(String name);
}

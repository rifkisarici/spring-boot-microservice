package com.microservice.productservice.application;

import com.microservice.productservice.application.command.ProductCommand;
import com.microservice.productservice.application.port.ProductPort;
import com.microservice.productservice.application.query.ProductViewModel;
import com.microservice.productservice.domain.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductCommandUseCase {
    private final ProductPort productPort;


    public String createProduct(ProductCommand productCommand) {
        Product product=productCommand.toDomainEntity();
        return productPort.createProduct(product);
    }

    public List<ProductViewModel> getAllProduct() {
        return productPort.getAllProduct();
    }
}

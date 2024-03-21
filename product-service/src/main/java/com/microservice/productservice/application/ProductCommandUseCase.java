package com.microservice.productservice.application;

import com.microservice.productservice.application.command.ProductCommand;
import com.microservice.productservice.application.command.UpdateProductCommand;
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


    public ProductViewModel createProduct(ProductCommand productCommand) {
        Product product=productCommand.toDomainEntity();
        return productPort.createProduct(product);
    }

    public List<ProductViewModel> getAllProduct() {
        return productPort.getAllProduct();
    }

    public ProductViewModel updateProduct(UpdateProductCommand updateProductCommand) {
        Product product=updateProductCommand.toDomainEntity();
        return productPort.updateProduct(product);
    }

    public List<ProductViewModel> getAllProductByName(String name) {
        return productPort.getAllProductByName(name);
    }
}

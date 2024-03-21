package com.microservice.productservice.application;

import com.microservice.productservice.application.command.ProductCommand;
import com.microservice.productservice.application.command.UpdateProductCommand;
import com.microservice.productservice.application.port.ProductPort;
import com.microservice.productservice.application.query.ProductViewModel;
import com.microservice.productservice.domain.Product;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductCommandUseCaseTest {
    @Mock
    private ProductPort productPort;
    @InjectMocks
    private ProductCommandUseCase productCommandUseCase;

    @Test
    void createProduct() {
        ProductCommand productCommand = mock(ProductCommand.class);
        when(productCommand.getDescription()).thenReturn("description");
        when(productCommand.getName()).thenReturn("name");
        when(productCommand.getPrice()).thenReturn("price");

        ProductViewModel productViewModel=new ProductViewModel("id","name","description","price");

        Product product=Product.builder()
                .id("-1")
                .name("name")
                .description("description")
                .price("price")
                .build();

        when(productCommand.toDomainEntity()).thenReturn(product);


        when(productPort.createProduct(product)).thenReturn(productViewModel);

        // Act
        ProductViewModel actualResponse = productCommandUseCase.createProduct(productCommand);

        // Assert
        assertEquals(productViewModel, actualResponse);
    }

    @Test
    void getAllProduct() {
        ProductViewModel productViewModel=new ProductViewModel("id","name","description","price");
        when(productPort.getAllProduct()).thenReturn(List.of(productViewModel));
        List<ProductViewModel> actualResponse = productCommandUseCase.getAllProduct();
        assertEquals(List.of(productViewModel), actualResponse);
    }

    @Test
    void updateProduct() {
        UpdateProductCommand updateProductCommand = mock(UpdateProductCommand.class);
        when(updateProductCommand.getId()).thenReturn("id");
        when(updateProductCommand.getName()).thenReturn("name");
        when(updateProductCommand.getDescription()).thenReturn("description");
        when(updateProductCommand.getPrice()).thenReturn("price");

        Product product=new Product("id","name","description","price");

        ProductViewModel productViewModel=new ProductViewModel("id","name","description","price");

        when(updateProductCommand.toDomainEntity()).thenReturn(product);

        when(productPort.updateProduct(product)).thenReturn(productViewModel);

        ProductViewModel actualResponse = productCommandUseCase.updateProduct(updateProductCommand);

        assertEquals(productViewModel, actualResponse);
    }

    @Test
    void getAllProductByName() {
        ProductViewModel productViewModel=new ProductViewModel("id","name","description","price");
        when(productPort.getAllProductByName("name")).thenReturn(List.of(productViewModel));
        List<ProductViewModel> actualResponse = productCommandUseCase.getAllProductByName("name");
        assertEquals(List.of(productViewModel), actualResponse);
    }
}

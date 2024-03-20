package com.microservice.productservice.application;

import com.microservice.productservice.application.command.ProductCommand;
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

        Product product=Product.builder()
                .id("-1")
                .name("name")
                .description("description")
                .price("price")
                .build();

        when(productCommand.toDomainEntity()).thenReturn(product);

        String expectedResponse = "successful";
        when(productPort.createProduct(product)).thenReturn(expectedResponse);

        // Act
        String actualResponse = productCommandUseCase.createProduct(productCommand);

        // Assert
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getAllProduct() {
        ProductViewModel productViewModel=new ProductViewModel("id","name","description","price");
        when(productPort.getAllProduct()).thenReturn(List.of(productViewModel));
        List<ProductViewModel> actualResponse = productCommandUseCase.getAllProduct();
        assertEquals(List.of(productViewModel), actualResponse);
    }

}

package com.microservice.productservice.infra.inp.web;

import com.microservice.productservice.application.ProductCommandUseCase;
import com.microservice.productservice.application.command.ProductCommand;
import com.microservice.productservice.application.query.ProductViewModel;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Mock
    private ProductCommandUseCase productCommandUseCase;
    @InjectMocks
    private ProductController productController;

    @Test
    void createProduct() {
        ProductCommand productCommand=ProductCommand.builder()
                .name("name")
                .description("description")
                .price("price")
                .build();

        String expectedResponse = "successful";
        when(productCommandUseCase.createProduct(productCommand)).thenReturn(expectedResponse);
        // Act
        String actualResponse = productController.createProduct(productCommand);

        // Assert
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getAllProduct() {
        ProductViewModel productViewModel=new ProductViewModel("id","name","description","price");
        when(productCommandUseCase.getAllProduct()).thenReturn(List.of(productViewModel));
        List<ProductViewModel> actualResponse = productController.getAllProduct();
        assertEquals(List.of(productViewModel), actualResponse);
    }
}

package com.microservice.productservice.infra.inp.web;

import com.microservice.productservice.application.ProductCommandUseCase;
import com.microservice.productservice.application.command.ProductCommand;
import com.microservice.productservice.application.command.UpdateProductCommand;
import com.microservice.productservice.application.query.ProductViewModel;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;
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
        ProductViewModel productViewModel=new ProductViewModel("id","name","description","price");

        ProductCommand productCommand=ProductCommand.builder()
                .name("name")
                .description("description")
                .price("price")
                .build();

        when(productCommandUseCase.createProduct(productCommand)).thenReturn(productViewModel);

        // Act
        ResponseEntity<Object> responseEntity = productController.createProduct(productCommand);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(productViewModel, ((Map<String, Object>) responseEntity.getBody()).get("data"));
    }

    @Test
    void getAllProduct() {
        ProductViewModel productViewModel=new ProductViewModel("id","name","description","price");
        when(productCommandUseCase.getAllProduct()).thenReturn(List.of(productViewModel));
        // Act
        ResponseEntity<Object> responseEntity = productController.getAllProduct();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(List.of(productViewModel), ((Map<String, Object>) responseEntity.getBody()).get("data"));
    }

    @Test
    void updateProduct() {
        ProductViewModel productViewModel=new ProductViewModel("id","name","description","price");
        UpdateProductCommand updateProductCommand=new UpdateProductCommand("id","name","description","price");
        when(productCommandUseCase.updateProduct(updateProductCommand)).thenReturn(productViewModel);
        // Act
        ResponseEntity<Object> responseEntity = productController.updateProduct(updateProductCommand);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(productViewModel, ((Map<String, Object>) responseEntity.getBody()).get("data"));
    }

    @Test
    void getAllProductByName() {
        ProductViewModel productViewModel=new ProductViewModel("id","name","description","price");
        when(productCommandUseCase.getAllProductByName("name")).thenReturn(List.of(productViewModel));
        // Act
        ResponseEntity<Object> responseEntity = productController.getAllProductByName("name");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(List.of(productViewModel), ((Map<String, Object>) responseEntity.getBody()).get("data"));
    }

}

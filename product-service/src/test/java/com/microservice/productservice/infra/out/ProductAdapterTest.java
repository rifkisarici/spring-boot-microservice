package com.microservice.productservice.infra.out;

import com.microservice.productservice.application.query.ProductViewModel;
import com.microservice.productservice.domain.Product;
import com.microservice.productservice.infra.out.entity.ProductEntity;
import com.microservice.productservice.infra.out.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductAdapterTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductMapper productMapper;
    @InjectMocks
    private ProductAdapter productAdapter;

    @Test
    void createProduct() {
        Product product=Product.builder()
                .name("name")
                .description("description")
                .price("price")
                .build();

        // Act
        String actualResponse = productAdapter.createProduct(product);
        assertEquals("successful", actualResponse);
        verify(productRepository, times(1)).save(any(ProductEntity.class));
    }

    @Test
    void getAllProduct() {
        ProductViewModel productViewModel=new ProductViewModel("id","name","description","price");
        ProductEntity productEntity=new ProductEntity("id","name","description","price");

        when(productMapper.toViewModel(List.of(productEntity))).thenReturn(List.of(productViewModel));
        when(productRepository.findAll()).thenReturn(List.of(productEntity));

        List<ProductViewModel> actualResponse = productAdapter.getAllProduct();
        assertEquals(List.of(productViewModel), actualResponse);
    }
}

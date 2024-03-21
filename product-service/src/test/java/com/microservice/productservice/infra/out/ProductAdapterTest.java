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
import java.util.Optional;

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

        ProductViewModel productViewModel=new ProductViewModel("id","name","description","price");
        // Act
        ProductViewModel actualResponse = productAdapter.createProduct(product);
        assertEquals(actualResponse.getName(), productViewModel.getName());
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

    @Test
    void updateProduct() {
        Product product=new Product("id","name","description","price");
        ProductViewModel productViewModel=new ProductViewModel("id","name","description","price");
        ProductEntity productEntity=new ProductEntity("id","name","description","price");

        when(productRepository.findById(product.getId())).thenReturn(Optional.of(productEntity));

        when(productMapper.toViewModel(productEntity)).thenReturn(productViewModel);

        ProductViewModel actualResponse = productAdapter.updateProduct(product);
        assertEquals(productViewModel, actualResponse);
        verify(productRepository, times(1)).save(productEntity);
    }

    @Test
    void getAllProductByName() {
        ProductViewModel productViewModel=new ProductViewModel("id","name","description","price");
        ProductEntity productEntity=new ProductEntity("id","name","description","price");

        when(productMapper.toViewModel(List.of(productEntity))).thenReturn(List.of(productViewModel));
        when(productRepository.findAllByName("nam")).thenReturn(List.of(productEntity));

        List<ProductViewModel> actualResponse = productAdapter.getAllProductByName("nam");
        assertEquals(List.of(productViewModel), actualResponse);
    }
}

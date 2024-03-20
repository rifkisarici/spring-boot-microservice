package com.microservice.productservice.infra.out.mapper;

import com.microservice.productservice.application.query.ProductViewModel;
import com.microservice.productservice.infra.out.entity.ProductEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductMapperTest {
    @InjectMocks
    private ProductMapper productMapper;

    /*@Before
    public void setUp() {
        productMapper = ProductMapper.getInstance();
    }*/

    @Test
    public void testToViewModel() {
        // Mocking ProductEntity
        ProductEntity productEntity = mock(ProductEntity.class);
        when(productEntity.getId()).thenReturn("1");
        when(productEntity.getDescription()).thenReturn("Test Description");
        when(productEntity.getName()).thenReturn("Test Product");
        when(productEntity.getPrice()).thenReturn("10.99");

        // Mapping ProductEntity to ProductViewModel
        ProductViewModel viewModel = productMapper.toViewModel(productEntity);

        // Assertion
        assertEquals("1", viewModel.getId());
        assertEquals("Test Description", viewModel.getDescription());
        assertEquals("Test Product", viewModel.getName());
        assertEquals("10.99", viewModel.getPrice());
    }

    @Test
    public void testToViewModelList() {
        // Mocking a list of ProductEntities
        List<ProductEntity> productEntities = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            ProductEntity productEntity = mock(ProductEntity.class);
            when(productEntity.getId()).thenReturn("1"+i);
            when(productEntity.getDescription()).thenReturn("Test Description"+i);
            when(productEntity.getName()).thenReturn("Test Product"+i);
            when(productEntity.getPrice()).thenReturn("10"+i);
            productEntities.add(productEntity);
        }

        // Mapping list of ProductEntities to list of ProductViewModels
        List<ProductViewModel> viewModels = productMapper.toViewModel(productEntities);

        // Assertion
        for (int i = 0; i < viewModels.size(); i++) {
            assertEquals("1"+(i+1), viewModels.get(i).getId());
            assertEquals("Test Description"+(i+1), viewModels.get(i).getDescription());
            assertEquals("Test Product"+(i+1), viewModels.get(i).getName());
            assertEquals("10"+(i+1), viewModels.get(i).getPrice());
        }

    }



}

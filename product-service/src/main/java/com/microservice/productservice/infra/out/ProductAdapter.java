package com.microservice.productservice.infra.out;

import com.microservice.productservice.application.port.ProductPort;
import com.microservice.productservice.application.query.ProductViewModel;
import com.microservice.productservice.domain.Product;
import com.microservice.productservice.infra.out.entity.ProductEntity;
import com.microservice.productservice.infra.out.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class ProductAdapter implements ProductPort {
    private final ProductRepository productRepository;
    @Override
    public ProductViewModel createProduct(Product product) {
        ProductEntity productEntity=ProductEntity.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
        productRepository.save(productEntity);
        log.info("product {} is saved",productEntity.getId());
        return ProductMapper.getInstance().toViewModel(productEntity);
    }

    @Override
    public List<ProductViewModel> getAllProduct() {
        return ProductMapper.getInstance().toViewModel(productRepository.findAll());
    }

    @Override
    public ProductViewModel updateProduct(Product product) {
        ProductEntity productEntity=productRepository.findById(product.getId()).orElseThrow();
        productEntity.setName(product.getName());
        productEntity.setDescription(product.getDescription());
        productEntity.setPrice(product.getPrice());
        productRepository.save(productEntity);
        return ProductMapper.getInstance().toViewModel(productEntity);
    }

    @Override
    public List<ProductViewModel> getAllProductByName(String name) {
        return ProductMapper.getInstance().toViewModel(productRepository.findAllByName(name));
    }


}

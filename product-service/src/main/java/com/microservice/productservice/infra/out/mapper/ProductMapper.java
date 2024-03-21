package com.microservice.productservice.infra.out.mapper;

import com.microservice.productservice.application.query.ProductViewModel;
import com.microservice.productservice.infra.out.entity.ProductEntity;

import java.util.List;

public class ProductMapper {
    private static final ProductMapper productMapper = new ProductMapper();
    public static ProductMapper getInstance(){
        return productMapper;
    }

    public ProductViewModel toViewModel(ProductEntity productEntities) {
        return new ProductViewModel(productEntities.getId(),productEntities.getName(),productEntities.getDescription(),productEntities.getPrice());
    }

    public List<ProductViewModel> toViewModel(List<ProductEntity> productEntities) {
        return productEntities.stream().map(this::toViewModel).toList();
    }


}

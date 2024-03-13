package com.microservice.productservice.infra.out;

import com.microservice.productservice.infra.out.entity.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductEntity,String> {
}

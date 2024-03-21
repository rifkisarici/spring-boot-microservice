package com.microservice.productservice.infra.out;

import com.microservice.productservice.infra.out.entity.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<ProductEntity,String> {
    @Query("{'name': { $regex: ?0, $options: 'i' }}")
    List<ProductEntity> findAllByName(String name);
}

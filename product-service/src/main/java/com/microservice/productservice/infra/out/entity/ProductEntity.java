package com.microservice.productservice.infra.out.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(value="product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductEntity {
    @Id
    private String id;

    @Field(name="name")// zorunlu alan haline gelir
    @Indexed(unique = true)
    private String name;


    private String description;

    @Field(name="price")
    private String price;
}

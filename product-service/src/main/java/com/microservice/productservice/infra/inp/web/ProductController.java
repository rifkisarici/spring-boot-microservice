package com.microservice.productservice.infra.inp.web;

import com.microservice.productservice.application.ProductCommandUseCase;
import com.microservice.productservice.application.command.ProductCommand;
import com.microservice.productservice.application.query.ProductViewModel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductCommandUseCase productCommandUseCase;

    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    public String createProduct(@RequestBody ProductCommand productCommand){
        return productCommandUseCase.createProduct(productCommand);
    }


    @GetMapping(value =  "")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductViewModel> getAllProduct(){
        return productCommandUseCase.getAllProduct();
    }

}

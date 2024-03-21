package com.microservice.productservice.infra.inp.web;

import com.microservice.productservice.application.ProductCommandUseCase;
import com.microservice.productservice.application.command.ProductCommand;
import com.microservice.productservice.application.command.UpdateProductCommand;
import com.microservice.productservice.application.query.ProductViewModel;
import lombok.AllArgsConstructor;
import org.microservices.handler.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductCommandUseCase productCommandUseCase;

    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createProduct(@RequestBody ProductCommand productCommand){
        ProductViewModel productViewModel=productCommandUseCase.createProduct(productCommand);
        return ResponseHandler.generateResponse("The Product has been successfully saved.",HttpStatus.OK, productViewModel);
    }

    @GetMapping(value =  "")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getAllProduct(){
        List<ProductViewModel>productViewModels=productCommandUseCase.getAllProduct();
        return ResponseHandler.generateResponse("Successfully retrieved data!",HttpStatus.OK, productViewModels);
    }

    @PutMapping(value =  "")
    public ResponseEntity<Object> updateProduct(@RequestBody UpdateProductCommand updateProductCommand){
        ProductViewModel productViewModel=productCommandUseCase.updateProduct(updateProductCommand);
        return ResponseHandler.generateResponse("Successfully retrieved data!",HttpStatus.OK, productViewModel);
    }


    @GetMapping(value =  "/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getAllProductByName(@PathVariable String name){
        List<ProductViewModel>productViewModels=productCommandUseCase.getAllProductByName(name);
        return ResponseHandler.generateResponse("Successfully retrieved data!",HttpStatus.OK, productViewModels);
    }

}

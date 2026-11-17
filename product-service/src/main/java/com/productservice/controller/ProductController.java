package com.productservice.controller;

import com.productservice.dto.ProductRequest;
import com.productservice.dto.ProductResponse;
import com.productservice.entity.Product;
import com.productservice.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    public final ProductService productService;

    public ProductController(ProductService productService){ this.productService = productService;}

    // Creating the product
    @PostMapping("/create")
    public ResponseEntity<ProductResponse> addProduct(@Valid @RequestBody ProductRequest productRequest){
        ProductResponse product = this.productService.addProduct(productRequest);
        return new ResponseEntity<>(product,HttpStatus.CREATED);
    }

    // Read the product
    @GetMapping("/read/{id}")
    public ResponseEntity<ProductResponse> readProduct(@PathVariable Long id){
        ProductResponse product = this.productService.getProduct(id);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    // Read all the product
    @GetMapping("/read")
    public ResponseEntity<List<ProductResponse>> readAllProduct(){
        List<ProductResponse> product = this.productService.getAllProduct();
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    // Delete the product
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        this.productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // Update the product
    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest productRequest) {

        ProductResponse productData = productService.updateProduct(id, productRequest);

        return new ResponseEntity<>(productData, HttpStatus.OK);
    }

}

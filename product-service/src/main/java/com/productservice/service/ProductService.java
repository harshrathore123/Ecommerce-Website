package com.productservice.service;

import com.productservice.dto.ProductRequest;
import com.productservice.entity.Product;
import com.productservice.exception.ProductNotFoundException;
import com.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    public final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    // Performing Crud Operation
    // Creating the product
    public Product addProduct(ProductRequest productRequest){
        Product product = new Product();

        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setActive(productRequest.getActive());
        product.setCategory(productRequest.getCategory());

        return this.productRepository.save(product);
    }

    // Read the product
    public Product getProduct(Long id){
        return this.productRepository.findById(id)
                .orElseThrow(()->
                            new ProductNotFoundException("Product not found for id: "+id)
                );

    }

    // Read all the product
    public List<Product> getAllProduct(){
        return this.productRepository.findAll();
    }

    // Delete the product
    public void deleteProduct(Long id){
        Product product = this.productRepository.findById(id)
                .orElseThrow(()->
                            new ProductNotFoundException("Product not found for id: "+id)
                );

        this.productRepository.delete(product);
    }

    // Update the product
    public Product updateProduct(Long id, ProductRequest productRequest) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found with id: " + id)
                );

        existingProduct.setName(productRequest.getName());
        existingProduct.setDescription(productRequest.getDescription());
        existingProduct.setPrice(productRequest.getPrice());
        existingProduct.setActive(productRequest.getActive());
        existingProduct.setCategory(productRequest.getCategory());

        return productRepository.save(existingProduct);
    }
}

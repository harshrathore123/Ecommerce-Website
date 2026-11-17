package com.productservice.service;

import com.productservice.dto.ProductRequest;
import com.productservice.dto.ProductResponse;
import com.productservice.entity.Product;
import com.productservice.exception.ProductNotFoundException;
import com.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    public final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    private ProductResponse mapToResponse(Product product){
        ProductResponse productResponse = new ProductResponse();

        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setPrice(product.getPrice());
        productResponse.setCategory(product.getCategory());
        productResponse.setActive(product.getActive());
        return productResponse;
    }

    // Performing Crud Operation
    // Creating the product
    public ProductResponse addProduct(ProductRequest productRequest){
        Product product = new Product();

        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setActive(productRequest.getActive());
        product.setCategory(productRequest.getCategory());

        Product saveProduct = productRepository.save(product);
        return mapToResponse(saveProduct);
    }

    // Read the product
    public ProductResponse getProduct(Long id){
        return mapToResponse(productRepository.findById(id)
                .orElseThrow(()->
                            new ProductNotFoundException("Product not found for id: "+id)
                ));

    }

    // Read all the product
    public List<ProductResponse> getAllProduct(){
        return this.productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
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
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found with id: " + id)
                );

        existingProduct.setName(productRequest.getName());
        existingProduct.setDescription(productRequest.getDescription());
        existingProduct.setPrice(productRequest.getPrice());
        existingProduct.setActive(productRequest.getActive());
        existingProduct.setCategory(productRequest.getCategory());

        return mapToResponse(productRepository.save(existingProduct));
    }
}

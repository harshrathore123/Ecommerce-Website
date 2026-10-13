package com.orderservice.client;

import com.orderservice.config.FeignClientConfig;
import com.orderservice.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service",url = "http://localhost:8082",configuration = FeignClientConfig.class)
public interface ProductClient {

    @GetMapping("/product/read/{id}")
    ProductResponse getProductById(@PathVariable Long id);

}

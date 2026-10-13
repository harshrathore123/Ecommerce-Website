package com.orderservice.config;

import com.orderservice.exception.ProductNotFoundException;
import com.orderservice.exception.UserNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignErrorDecoder implements ErrorDecoder {

    public final ErrorDecoder defaultErrorDecoder = new ErrorDecoder.Default();

    @Override
    public Exception decode(String methodKey, Response response){

        if(response.status() == 404){

            if(methodKey.contains("UserClient")){
                return new UserNotFoundException(
                        "User not found in User Service"
                );
            }

            if(methodKey.contains("ProductClient")){
                return new ProductNotFoundException(
                        "Product not found in Product Service"
                );
            }
        }

        return defaultErrorDecoder.decode(methodKey,response);
    }
}

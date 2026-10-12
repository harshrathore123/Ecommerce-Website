package com.orderservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {

    @NotNull(message = "User Id is required")
    private Long userId;

    @NotNull(message = "Product Id is required")
    private Long productId;

    @NotNull(message = "Quantity is required")
    @Min(value = 1,message = "Quantity must be atleast one")
    private Integer quantity;

    @NotNull(message = "Total Price is required")
    @Min(value = 1,message = "Total price must be greater than 0")
    private Double totalPrice;

    @NotNull(message = "Status is required")
    private String status;
}

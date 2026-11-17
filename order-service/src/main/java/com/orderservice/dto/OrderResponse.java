package com.orderservice.dto;

import com.orderservice.entity.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderResponse {
    private Long id;
    private Long userId;
    private Long productId;
    private Integer quantity;
    private Double totalPrice;
    private OrderStatus status;
    private Long customerId;
}

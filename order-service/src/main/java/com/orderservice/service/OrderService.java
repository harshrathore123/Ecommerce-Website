package com.orderservice.service;

import com.orderservice.client.ProductClient;
import com.orderservice.client.UserClient;
import com.orderservice.dto.OrderRequest;
import com.orderservice.dto.ProductResponse;
import com.orderservice.dto.UserResponse;
import com.orderservice.entity.Order;
import com.orderservice.exception.OrderNotFoundException;
import com.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    public final OrderRepository orderRepository;
    public final UserClient userClient;
    public final ProductClient productClient;

    public OrderService(OrderRepository orderRepository, UserClient userClient, ProductClient productClient){
        this.orderRepository = orderRepository;
        this.userClient = userClient;
        this.productClient = productClient;
    }

    // Todo:Perform Rest API
    // Todo: Create Order
    public Order createOrder(OrderRequest orderRequest){

        UserResponse user = this.userClient.getUserById(orderRequest.getUserId());
        ProductResponse product = this.productClient.getProductById(orderRequest.getProductId());

        Order order = new Order();

        order.setUserId(user.getId());
        order.setProductId(product.getId());
        order.setQuantity(orderRequest.getQuantity());
        order.setStatus(orderRequest.getStatus());
        order.setTotalPrice(orderRequest.getTotalPrice());

        return this.orderRepository.save(order);
    }

    // Todo: Read Single Order
    public Order getOrderById(Long id){
        return this.orderRepository.findById(id)
                .orElseThrow(()->
                new OrderNotFoundException("Order not found for id: "+id)
        );
    }

    // Todo: Read All Order
    public List<Order> getOrderAll(){
        return this.orderRepository.findAll();
    }

    // Todo: Delete Order By Id
    public void deleteOrderById(Long id){
        Order order = this.orderRepository.findById(id)
                .orElseThrow(()->
                        new OrderNotFoundException("Order not found for id: "+id)
                );
        this.orderRepository.delete(order);        ;
    }

    // Todo: Delete Order All
    public void deleteOrder(){
        this.orderRepository.deleteAll();
    }

    // Todo: Update Order
    public Order updateOrder(OrderRequest orderRequest,Long id){
        Order order = this.orderRepository.findById(id)
                .orElseThrow(()->
                        new OrderNotFoundException("Order not found for id: "+id)
                );                ;
                order.setUserId(orderRequest.getUserId());
                order.setProductId(orderRequest.getProductId());
                order.setQuantity(orderRequest.getQuantity());
                order.setStatus(orderRequest.getStatus());
                order.setTotalPrice(orderRequest.getTotalPrice());

            return this.orderRepository.save(order);
    }
}

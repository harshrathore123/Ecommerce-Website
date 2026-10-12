package com.orderservice.service;

import com.orderservice.dto.OrderRequest;
import com.orderservice.entity.Order;
import com.orderservice.exception.OrderNotFoundException;
import com.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    public final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    // Todo:Perform Rest API
    // Todo: Create Order
    public Order createOrder(OrderRequest orderRequest){
        Order order = new Order();

        order.setUserId(orderRequest.getUserId());
        order.setProductId(orderRequest.getProductId());
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

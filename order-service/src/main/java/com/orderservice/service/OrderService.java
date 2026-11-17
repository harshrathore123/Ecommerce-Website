package com.orderservice.service;

import com.orderservice.client.ProductClient;
import com.orderservice.client.UserClient;
import com.orderservice.dto.OrderRequest;
import com.orderservice.dto.OrderResponse;
import com.orderservice.dto.ProductResponse;
import com.orderservice.dto.UserResponse;
import com.orderservice.entity.Customer;
import com.orderservice.entity.Order;
import com.orderservice.exception.OrderNotFoundException;
import com.orderservice.repository.CustomerRepository;
import com.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    public final OrderRepository orderRepository;
    public final CustomerRepository customerRepository;
    public final UserClient userClient;
    public final ProductClient productClient;

    public OrderService(CustomerRepository customerRepository, OrderRepository orderRepository, UserClient userClient, ProductClient productClient){
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.userClient = userClient;
        this.productClient = productClient;
    }

    private OrderResponse mapToResponse(Order order){
        OrderResponse orderResponse = new OrderResponse();

        orderResponse.setId(order.getId());
        orderResponse.setCustomerId(
                order.getCustomer() != null ? order.getCustomer().getId() : null
        );
        orderResponse.setUserId(order.getUserId());
        orderResponse.setProductId(order.getProductId());
        orderResponse.setQuantity(order.getQuantity());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setTotalPrice(order.getTotalPrice());

        return orderResponse;
    }

    // Todo: Perform Rest API
    // Todo: Create Order
    public OrderResponse createOrder(OrderRequest orderRequest){

        UserResponse user = this.userClient.getUserById(orderRequest.getUserId());
        ProductResponse product = this.productClient.getProductById(orderRequest.getProductId());

        Customer customer  = this.customerRepository.findById(orderRequest.getCustomerId()).orElseThrow(()->
                new RuntimeException("Customer not found for id: "+orderRequest.getCustomerId()));

        Order order = new Order();

        order.setUserId(user.getId());
        order.setProductId(product.getId());
        order.setCustomer(customer);
        order.setQuantity(orderRequest.getQuantity());
        order.setStatus(orderRequest.getStatus());
        order.setTotalPrice(orderRequest.getTotalPrice());

        Order savedOrder = this.orderRepository.save(order);
        return mapToResponse(savedOrder);

    }

    // Todo: Read Single Order
    public OrderResponse getOrderById(Long id){
        return mapToResponse(this.orderRepository.findById(id)
                .orElseThrow(()->
                new OrderNotFoundException("Order not found for id: "+id)
        ));
    }

    // Todo: Read All Order
    public List<OrderResponse> getOrderAll(){
        return this.orderRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
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
    public OrderResponse updateOrder(OrderRequest orderRequest,Long id){
        Customer customer = this.customerRepository.findById(orderRequest.getCustomerId()).orElseThrow(()->
                new RuntimeException("Customer not found for id: "+orderRequest.getCustomerId()));

        Order order = this.orderRepository.findById(id)
                .orElseThrow(()->
                        new OrderNotFoundException("Order not found for id: "+id)
                );                ;
                order.setUserId(orderRequest.getUserId());
                order.setProductId(orderRequest.getProductId());
                order.setQuantity(orderRequest.getQuantity());
                order.setStatus(orderRequest.getStatus());
                order.setCustomer(customer);
                order.setTotalPrice(orderRequest.getTotalPrice());


            return mapToResponse(this.orderRepository.save(order));
    }
}

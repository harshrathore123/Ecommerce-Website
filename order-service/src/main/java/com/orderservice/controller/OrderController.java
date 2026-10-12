package com.orderservice.controller;

import com.orderservice.dto.OrderRequest;
import com.orderservice.entity.Order;
import com.orderservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    public final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    // Todo: Handler for Crud Operation
    // Todo: Creating Handler for Create Order
    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@Valid @RequestBody OrderRequest orderRequest){
        Order order = this.orderService.createOrder(orderRequest);
        return new ResponseEntity<>(order,HttpStatus.CREATED);
    }

    // Todo: Reading Handler for Read Order
    @GetMapping("/read/{id}")
    public ResponseEntity<Order> readOrder(@PathVariable Long id){
        Order order = this.orderService.getOrderById(id);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

    // Todo: Reading Hanlder for Read All Order
    @GetMapping("/read")
    public ResponseEntity<List<Order>> readAllOrder(){
        List<Order> order = this.orderService.getOrderAll();
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

    // Todo: Delete Handler for Delete Order By Id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable Long id){
        this.orderService.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }

    // Todo: Delete Handler for Delete Order All
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteOrder(){
        this.orderService.deleteOrder();
        return ResponseEntity.noContent().build();
    }

    // Todo: Update Handler for Update Order
    @PutMapping("/update/{id}")
    public ResponseEntity<Order> updateOrder(@Valid @RequestBody OrderRequest orderRequest,@PathVariable Long id){
        Order order = this.orderService.updateOrder(orderRequest,id);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }
}

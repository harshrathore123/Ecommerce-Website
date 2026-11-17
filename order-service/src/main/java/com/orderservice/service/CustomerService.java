package com.orderservice.service;

import com.orderservice.entity.Customer;
import com.orderservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer){
        if(customer.getOrders() != null){
            customer.getOrders().forEach((order)->
                    order.setCustomer(customer));
        };

        return customerRepository.save(customer);
    }

    public Customer getCustomerById(Long id){
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public void deleteCustomer(Long id){
        Customer customer = getCustomerById(id);
        customerRepository.delete(customer);
    }



}

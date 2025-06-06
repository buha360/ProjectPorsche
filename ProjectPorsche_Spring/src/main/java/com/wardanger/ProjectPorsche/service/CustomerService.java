package com.wardanger.ProjectPorsche.service;

import com.wardanger.ProjectPorsche.dto.RegisterRequest;
import com.wardanger.ProjectPorsche.model.Customer;
import com.wardanger.ProjectPorsche.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer register(RegisterRequest request) {
        Customer user = new Customer();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAddress(request.getAddress());
        return customerRepository.save(user);
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Optional<Customer> getCustomerByUsername(String username) {
        return customerRepository.findByUsername(username);
    }
}
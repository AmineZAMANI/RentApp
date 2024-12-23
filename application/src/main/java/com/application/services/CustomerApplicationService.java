package com.application.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.domain.Customer;
import com.domain.repositories.Customers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerApplicationService {
	    private final Customers customers;

	    public Optional<Customer> findCustomer(String email) {
	    	return customers.searchByEmail(email);
	    }
	    
}

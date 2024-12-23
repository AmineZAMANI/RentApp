package com.web.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.application.services.CustomerApplicationService;
import com.common.dtos.CustomerDTO;
import com.common.mappers.CustomerMapper;
import com.domain.Customer;

@Service
public class CustomerWebService {

	private final CustomerMapper customerMapper;
	private final CustomerApplicationService customerApplicationService;

	public CustomerWebService(CustomerMapper customerMapper, CustomerApplicationService customerApplicationService) {
		this.customerMapper = customerMapper;
		this.customerApplicationService = customerApplicationService;
	}

	public CustomerDTO findCustomer(String email) {
		Optional<Customer> customer = customerApplicationService.findCustomer(email);
		return customerMapper.toDTO(customer.get());
	}

}

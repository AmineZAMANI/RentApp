package com.infra.adapters;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.domain.Customer;
import com.domain.repositories.Customers;
import com.infra.entity.CustomerEntity;
import com.infra.entity.mappers.CustomerEntityMapper;
import com.infra.repositories.CustomerRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Repository
public class CustomersImpl implements Customers {

	private final CustomerRepository customerJpaRepository;
	private final CustomerEntityMapper customerMapper;

	@Override
	public Optional<Customer> searchByEmail(String email) {
		log.info("Searching customer by license number: {} or email: {}", email);

		Optional<CustomerEntity> customerJpa = customerJpaRepository.findCustomerJpaByEmail(email);

		if (customerJpa.isEmpty()) {
			log.warn("No customer found for license number: {} or email: {}", email);
		}

		return Optional.of(customerMapper.toModel(customerJpa.get()));
	}

	@Override
	public Customer save(Customer customer) {
		log.info("Saving customer: {}", customer);

		CustomerEntity customerJpa = customerMapper.toEntity(customer);
		CustomerEntity savedCustomerJpa = customerJpaRepository.save(customerJpa);

		log.info("Customer saved with ID: {}", savedCustomerJpa.getId());
		return customerMapper.toModel(savedCustomerJpa);
	}

}

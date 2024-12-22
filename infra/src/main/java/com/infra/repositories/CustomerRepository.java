package com.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.domain.Customer;
import com.infra.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}


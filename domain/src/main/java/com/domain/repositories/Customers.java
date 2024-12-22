package com.domain.repositories;

import java.util.Optional;

import com.domain.Customer;

public interface Customers {
    Optional<com.domain.Customer> searchByEmail( String email );
    Customer save(Customer customer);
}

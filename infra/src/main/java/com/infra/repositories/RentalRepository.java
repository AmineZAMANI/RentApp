package com.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.domain.Rental;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}


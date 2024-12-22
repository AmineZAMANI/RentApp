package com.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infra.entity.RentalEntity;

public interface RentalRepository extends JpaRepository<RentalEntity, Long> {
}


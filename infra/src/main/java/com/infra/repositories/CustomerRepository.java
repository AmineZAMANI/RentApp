package com.infra.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.infra.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

	@Query("SELECT c FROM CustomerEntity c " + "WHERE (:email IS NULL OR c.email = :email)")
	Optional<CustomerEntity> findCustomerJpaByEmail(@Param("email") String email);
}

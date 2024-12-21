package com.application.mappers;

import org.mapstruct.Mapper;

import com.application.dtos.CustomerDTO;
import com.domain.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO toDTO(Customer customer);
    Customer toEntity(CustomerDTO customerDTO);
}

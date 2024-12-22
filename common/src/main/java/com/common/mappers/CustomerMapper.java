package com.common.mappers;

import org.mapstruct.Mapper;

import com.common.dtos.CustomerDTO;
import com.domain.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO toDTO(Customer customer);
    Customer toEntity(CustomerDTO customerDTO);
}

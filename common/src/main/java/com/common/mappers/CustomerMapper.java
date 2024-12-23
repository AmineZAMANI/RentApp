package com.common.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.common.dtos.CustomerDTO;
import com.domain.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
	@Mapping(source = "id", target = "id")
	@Mapping(source = "email", target = "email")
	@Mapping(source = "name", target = "name")
    CustomerDTO toDTO(Customer customer);
	
	@Mapping(source = "id", target = "id")
	@Mapping(source = "email", target = "email")
	@Mapping(source = "name", target = "name")
    Customer toDomain(CustomerDTO customerDTO);
}

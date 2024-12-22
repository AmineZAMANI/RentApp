package com.infra.entity.mappers;

import org.mapstruct.Mapper;

import com.domain.Customer;
import com.infra.entity.CustomerEntity;

@Mapper(componentModel = "spring")
public interface CustomerEntityMapper {
    Customer toModel(CustomerEntity customerentity);
    CustomerEntity toEntity(Customer customer);
}

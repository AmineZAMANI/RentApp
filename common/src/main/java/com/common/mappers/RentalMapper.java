package com.common.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.common.dtos.RentalDTO;
import com.domain.Rental;

@Mapper(componentModel = "spring", uses = {CarMapper.class, CustomerMapper.class})
public interface RentalMapper {

    @Mapping(source = "car.id", target = "carId")
    @Mapping(source = "customer.id", target = "customerId")
    RentalDTO toDTO(Rental rental);

    @Mapping(source = "carId", target = "car.id")
    @Mapping(source = "customerId", target = "customer.id")
    Rental toEntity(RentalDTO rentalDTO);
}

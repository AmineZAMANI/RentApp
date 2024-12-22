package com.common.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.common.dtos.RentalDTO;
import com.domain.Rental;

@Mapper(componentModel = "spring", uses = {CarMapper.class, CustomerMapper.class})
public interface RentalMapper {

    
    @Mapping(source = "id", target = "id")
    @Mapping(source = "car.id", target = "carId")
    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "rentalPeriod.startDate", target = "startDate")
    @Mapping(source = "rentalPeriod.endDate", target = "endDate")
    RentalDTO toDTO(Rental rental);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "carId", target = "car.id")
    @Mapping(source = "customerId", target = "customer.id")
    @Mapping(source = "startDate", target = "rentalPeriod.startDate")
    @Mapping(source = "endDate", target = "rentalPeriod.endDate")
    Rental toDomain(RentalDTO rentalDTO);
}

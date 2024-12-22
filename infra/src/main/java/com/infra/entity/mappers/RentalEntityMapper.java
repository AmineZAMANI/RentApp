package com.infra.entity.mappers;

import org.mapstruct.Mapper;

import com.domain.Rental;
import com.infra.entity.RentalEntity;

@Mapper(componentModel = "spring", uses = {CarEntityMapper.class, CustomerEntityMapper.class})
public interface RentalEntityMapper {

    Rental toModel(RentalEntity rental);

    RentalEntity toEntity(Rental rental);
}

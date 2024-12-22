package com.application.config;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Configuration;

import com.common.mappers.CarMapper;
import com.common.mappers.CustomerMapper;
import com.common.mappers.RentalMapper;
import com.infra.entity.mappers.CarEntityMapper;
import com.infra.entity.mappers.CustomerEntityMapper;
import com.infra.entity.mappers.RentalEntityMapper;

@Configuration
public class MapperConfig {
    public static final CarEntityMapper CAR_ENT_MAPPER = Mappers.getMapper(CarEntityMapper.class);
    public static final CustomerEntityMapper CUSTOMER_ENT_MAPPER = Mappers.getMapper(CustomerEntityMapper.class);
    public static final RentalEntityMapper RENTAL_ENT_MAPPER = Mappers.getMapper(RentalEntityMapper.class);
    public static final CarMapper CAR_MAPPER = Mappers.getMapper(CarMapper.class);
    public static final CustomerMapper CUSTOMER_MAPPER = Mappers.getMapper(CustomerMapper.class);
    public static final RentalMapper RENTAL_MAPPER = Mappers.getMapper(RentalMapper.class);
}

package com.application.mappers;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    public static final CarMapper CAR_MAPPER = Mappers.getMapper(CarMapper.class);
    public static final CustomerMapper CUSTOMER_MAPPER = Mappers.getMapper(CustomerMapper.class);
    public static final RentalMapper RENTAL_MAPPER = Mappers.getMapper(RentalMapper.class);
}

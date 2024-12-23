package com.common.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDTO {
    private Long id;
    private String name;
    private String email;
}


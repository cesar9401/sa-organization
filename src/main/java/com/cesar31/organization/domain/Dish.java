package com.cesar31.organization.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class Dish {

    private UUID dishId;
    private UUID organizationId;
    private String name;
    private String description;
    private Integer stock;
    private BigDecimal price;
}

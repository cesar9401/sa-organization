package com.cesar31.organization.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class Dish {

    private UUID disId;
    private UUID organizationId;
    private String name;
    private String description;
    private BigDecimal price;
}

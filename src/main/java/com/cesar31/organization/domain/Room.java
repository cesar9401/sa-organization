package com.cesar31.organization.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class Room {

    private UUID roomId;
    private UUID organizationId;
    private String code;
    private Integer capacity;
    private BigDecimal dailyPrice;
    private BigDecimal dailyMaintenanceCost;
}

package com.cesar31.organization.application.dto;

import com.cesar31.organization.application.util.SelfValidating;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class CreateRoomReqDto extends SelfValidating {

    private UUID organizationId;

    @NotNull
    @NotEmpty
    private String code;

    @NotNull
    @Positive
    private Integer capacity;

    @NotNull
    @Positive
    @Digits(integer = 15, fraction = 2)
    private BigDecimal dailyPrice;

    @NotNull
    @Positive
    @Digits(integer = 15, fraction = 2)
    private BigDecimal dailyMaintenanceCost;
}

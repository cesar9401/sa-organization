package com.cesar31.organization.application.dto;

import com.cesar31.organization.application.util.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class CreateDishReqDto extends SelfValidating {

    private UUID organizationId;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @PositiveOrZero
    private Integer stock;

    @NotNull
    @Positive
    private BigDecimal price;
}

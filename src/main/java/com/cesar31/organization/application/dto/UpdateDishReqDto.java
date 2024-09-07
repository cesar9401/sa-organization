package com.cesar31.organization.application.dto;

import com.cesar31.organization.application.util.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class UpdateDishReqDto extends SelfValidating {

    @NotNull
    private UUID disId;

    private UUID organizationId;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @Positive
    private BigDecimal price;
}

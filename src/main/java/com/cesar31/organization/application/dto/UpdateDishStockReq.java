package com.cesar31.organization.application.dto;

import com.cesar31.organization.application.util.SelfValidating;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UpdateDishStockReq extends SelfValidating {

    @NotNull
    private UUID clientId;

    @NotNull
    @NotEmpty
    private List<@Valid DishOrderDto> orders;

    @Getter
    @Setter
    public static class DishOrderDto {
        @NotNull
        private UUID dishId;

        @NotNull
        @Positive
        private Integer quantity;
    }
}

package com.cesar31.organization.application.ports.input;

import com.cesar31.organization.application.dto.CreateDishReqDto;
import com.cesar31.organization.application.dto.UpdateDishReqDto;
import com.cesar31.organization.domain.Dish;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DishUseCase {

    List<Dish> findAll(String dishIds) throws Exception;

    Optional<Dish> findById(UUID dishId) throws Exception;

    Dish save(CreateDishReqDto reqDto) throws Exception;

    Dish update(UUID dishId, UpdateDishReqDto reqDto) throws Exception;
}

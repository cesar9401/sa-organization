package com.cesar31.organization.application.mapper;

import com.cesar31.organization.application.dto.CreateDishReqDto;
import com.cesar31.organization.application.dto.UpdateDishReqDto;
import com.cesar31.organization.domain.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface DishMapper {

    @Mapping(source = "organizationId", target = "organizationId")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "stock", target = "stock")
    @Mapping(source = "price", target = "price")
    Dish toDish(CreateDishReqDto reqDto);

    @Mapping(source = "dishId", target = "dishId")
    @Mapping(source = "organizationId", target = "organizationId")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "stock", target = "stock")
    @Mapping(source = "price", target = "price")
    Dish toDish(UpdateDishReqDto reqDto);
}

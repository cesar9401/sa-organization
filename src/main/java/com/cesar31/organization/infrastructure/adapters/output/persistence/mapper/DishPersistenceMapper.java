package com.cesar31.organization.infrastructure.adapters.output.persistence.mapper;

import com.cesar31.organization.domain.Dish;
import com.cesar31.organization.infrastructure.adapters.output.persistence.entity.DishEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface DishPersistenceMapper {

    @Mapping(source = "dishId", target = "dishId")
    @Mapping(source = "organizationId", target = "organizationId")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "stock", target = "stock")
    @Mapping(source = "price", target = "price")
    Dish toDish(DishEntity entity);
    List<Dish> toDishes(List<DishEntity> entities);

    @InheritInverseConfiguration
    DishEntity toDishEntity(Dish dish);
    List<DishEntity> toDishEntities(List<Dish> dishes);
}

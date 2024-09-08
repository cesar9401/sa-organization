package com.cesar31.organization.application.ports.output;

import com.cesar31.organization.domain.Dish;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface DishOutputPort {

    List<Dish> findAll(UUID organizationId);

    List<Dish> findAllByDishIdInAndOrganizationId(Set<UUID> dishIds, UUID organizationId);

    Optional<Dish> findByDishIdAndOrganizationId(UUID dishId, UUID organizationId);

    Dish save(Dish dish);
}

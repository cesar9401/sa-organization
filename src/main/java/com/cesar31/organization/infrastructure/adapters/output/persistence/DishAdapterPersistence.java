package com.cesar31.organization.infrastructure.adapters.output.persistence;

import com.cesar31.organization.application.ports.output.DishOutputPort;
import com.cesar31.organization.domain.Dish;
import com.cesar31.organization.infrastructure.adapters.output.persistence.mapper.DishPersistenceMapper;
import com.cesar31.organization.infrastructure.adapters.output.persistence.repository.DishEntityRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class DishAdapterPersistence implements DishOutputPort {

    private final DishEntityRepository repository;
    private final DishPersistenceMapper mapper;

    public DishAdapterPersistence(DishEntityRepository repository, DishPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Dish> findAll(UUID organizationId) {
        var dishes = repository.findAllByOrganizationId(organizationId);
        return mapper.toDishes(dishes);
    }

    @Override
    public Optional<Dish> findByDishIdAndOrganizationId(UUID dishId, UUID organizationId) {
        return repository.findByDishIdAndOrganizationId(dishId, organizationId)
                .map(mapper::toDish);
    }

    @Override
    public Dish save(Dish dish) {
        var entity = mapper.toDishEntity(dish);
        var newEntity = repository.save(entity);
        return mapper.toDish(newEntity);
    }
}

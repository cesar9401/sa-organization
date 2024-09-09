package com.cesar31.organization.infrastructure.adapters.output.persistence;

import com.cesar31.organization.application.ports.output.DishOutputPort;
import com.cesar31.organization.domain.Dish;
import com.cesar31.organization.infrastructure.adapters.output.persistence.entity.DishEntity;
import com.cesar31.organization.infrastructure.adapters.output.persistence.mapper.DishPersistenceMapper;
import com.cesar31.organization.infrastructure.adapters.output.persistence.repository.DishEntityRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Component
public class DishAdapterPersistence implements DishOutputPort {

    private final DishEntityRepository repository;
    private final DishPersistenceMapper mapper;
    private final EntityManager entityManager;

    public DishAdapterPersistence(DishEntityRepository repository, DishPersistenceMapper mapper, EntityManager entityManager) {
        this.repository = repository;
        this.mapper = mapper;
        this.entityManager = entityManager;
    }

    @Override
    public List<Dish> findAllByQuery(UUID organizationId, String dishIds) {
        var cb = entityManager.getCriteriaBuilder();

        var cq = cb.createQuery(DishEntity.class);
        var dish = cq.from(DishEntity.class);

        var predicates = new ArrayList<Predicate>();

        if (organizationId != null) predicates.add(cb.equal(dish.get("organizationId"), organizationId));
        if (dishIds != null && !dishIds.isBlank()) {
            var ids = Arrays.stream(dishIds.split(","))
                    .map(UUID::fromString)
                    .distinct()
                    .toList();

            predicates.add(dish.get("dishId").in(new HashSet<>(ids)));
        }

        cq.where(predicates.toArray(new Predicate[]{}));
        var query = entityManager.createQuery(cq);
        var dishes = query.getResultList();
        return mapper.toDishes(dishes);
    }

    @Override
    public List<Dish> findAllByDishIdInAndOrganizationId(Set<UUID> dishIds, UUID organizationId) {
        var dishes = repository.findAllByDishIdInAndOrganizationId(dishIds, organizationId);
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

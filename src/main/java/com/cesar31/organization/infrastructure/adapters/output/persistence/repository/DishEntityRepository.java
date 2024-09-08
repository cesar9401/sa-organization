package com.cesar31.organization.infrastructure.adapters.output.persistence.repository;

import com.cesar31.organization.infrastructure.adapters.output.persistence.entity.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface DishEntityRepository extends JpaRepository<DishEntity, UUID> {

    List<DishEntity> findAllByDishIdInAndOrganizationId(Set<UUID> dishIds, UUID organizationId);

    List<DishEntity> findAllByOrganizationId(UUID organizationId);

    Optional<DishEntity> findByDishIdAndOrganizationId(UUID id, UUID organizationId);
}

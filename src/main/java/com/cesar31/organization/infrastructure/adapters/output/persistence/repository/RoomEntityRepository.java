package com.cesar31.organization.infrastructure.adapters.output.persistence.repository;

import com.cesar31.organization.infrastructure.adapters.output.persistence.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomEntityRepository extends JpaRepository<RoomEntity, UUID> {

    List<RoomEntity> findAllByOrganizationId(UUID organizationId);

    Optional<RoomEntity> findByRoomIdAndOrganizationId(UUID roomId, UUID organizationId);

    Optional<RoomEntity> findByCodeAndOrganizationId(String code, UUID organizationId);

    Optional<RoomEntity> findByRoomIdNotAndCodeAndOrganizationId(UUID roomId, String code, UUID organizationId);
}

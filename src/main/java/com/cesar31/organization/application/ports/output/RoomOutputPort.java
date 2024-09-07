package com.cesar31.organization.application.ports.output;

import com.cesar31.organization.domain.Room;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomOutputPort {

    List<Room> findAll(UUID organizationId);

    Optional<Room> findByRoomIdAndOrganizationId(UUID roomId, UUID organizationId);

    Room save(Room room);

    Boolean existsByCodeAndOrganizationIdAndRoomIdNot(String code, UUID organizationId, UUID roomId);
}

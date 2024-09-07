package com.cesar31.organization.infrastructure.adapters.output.persistence;

import com.cesar31.organization.application.ports.output.RoomOutputPort;
import com.cesar31.organization.domain.Room;
import com.cesar31.organization.infrastructure.adapters.output.persistence.mapper.DishPersistenceMapper;
import com.cesar31.organization.infrastructure.adapters.output.persistence.mapper.RoomPersistenceMapper;
import com.cesar31.organization.infrastructure.adapters.output.persistence.repository.RoomEntityRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class RoomAdapterPersistence implements RoomOutputPort {

    private final RoomEntityRepository repository;
    private final RoomPersistenceMapper mapper;

    public RoomAdapterPersistence(RoomEntityRepository repository, RoomPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Room> findAll(UUID organizationId) {
        var rooms = repository.findAllByOrganizationId(organizationId);
        return mapper.toRooms(rooms);
    }

    @Override
    public Optional<Room> findByRoomIdAndOrganizationId(UUID roomId, UUID organizationId) {
        return repository.findByRoomIdAndOrganizationId(roomId, organizationId)
                .map(mapper::toRoom);
    }

    @Override
    public Room save(Room room) {
        var entity = mapper.toRoomEntity(room);
        var newEntity = repository.save(entity);
        return mapper.toRoom(newEntity);
    }

    @Override
    public Boolean existsByCodeAndOrganizationIdAndRoomIdNot(String code, UUID organizationId, UUID roomId) {
        if (roomId == null) return repository.findByCodeAndOrganizationId(code, organizationId).isPresent();
        return repository.findByRoomIdNotAndCodeAndOrganizationId(roomId, code, organizationId).isPresent();
    }
}

package com.cesar31.organization.infrastructure.adapters.output.persistence.mapper;

import com.cesar31.organization.domain.Room;
import com.cesar31.organization.infrastructure.adapters.output.persistence.entity.RoomEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface RoomPersistenceMapper {

    @Mapping(source = "roomId", target = "roomId")
    @Mapping(source = "organizationId", target = "organizationId")
    @Mapping(source = "code", target = "code")
    @Mapping(source = "numberOfBeds", target = "capacity")
    @Mapping(source = "dailyPrice", target = "dailyPrice")
    @Mapping(source = "dailyMaintenanceCost", target = "dailyMaintenanceCost")
    Room toRoom(RoomEntity entity);
    List<Room> toRooms(List<RoomEntity> entities);

    @InheritInverseConfiguration
    RoomEntity toRoomEntity(Room room);
    List<RoomEntity> toRoomEntities(List<Room> rooms);
}

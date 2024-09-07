package com.cesar31.organization.application.mapper;

import com.cesar31.organization.application.dto.CreateRoomReqDto;
import com.cesar31.organization.application.dto.UpdateRoomReqDto;
import com.cesar31.organization.domain.Room;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RoomMapper {

    @Mapping(source = "organizationId", target = "organizationId")
    @Mapping(source = "code", target = "code")
    @Mapping(source = "capacity", target = "capacity")
    @Mapping(source = "dailyPrice", target = "dailyPrice")
    @Mapping(source = "dailyMaintenanceCost", target = "dailyMaintenanceCost")
    Room toRoom(CreateRoomReqDto reqDto);

    @Mapping(source = "roomId", target = "roomId")
    @InheritConfiguration
    Room toRoom(UpdateRoomReqDto reqDto);
}

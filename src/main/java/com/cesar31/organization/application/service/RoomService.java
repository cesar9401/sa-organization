package com.cesar31.organization.application.service;

import com.cesar31.organization.application.dto.CreateRoomReqDto;
import com.cesar31.organization.application.dto.UpdateRoomReqDto;
import com.cesar31.organization.application.exception.ApplicationException;
import com.cesar31.organization.application.exception.EntityNotFoundException;
import com.cesar31.organization.application.exception.ForbiddenException;
import com.cesar31.organization.application.mapper.RoomMapper;
import com.cesar31.organization.application.ports.input.OrganizationUseCase;
import com.cesar31.organization.application.ports.input.RoomUseCase;
import com.cesar31.organization.application.ports.output.CurrentUserOutputPort;
import com.cesar31.organization.application.ports.output.RoomOutputPort;
import com.cesar31.organization.application.util.enums.CategoryEnum;
import com.cesar31.organization.application.util.enums.RoleEnum;
import com.cesar31.organization.domain.Room;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class RoomService implements RoomUseCase {

    private final RoomOutputPort roomOutputPort;
    private final RoomMapper roomMapper;
    private final OrganizationUseCase organizationUseCase;
    private final CurrentUserOutputPort currentUserOutputPort;

    private final Set<UUID> allowedRoles = Set.of(RoleEnum.ROOT.roleId, RoleEnum.HOTEL_MANAGER.roleId);

    public RoomService(
            RoomOutputPort roomOutputPort,
            RoomMapper roomMapper,
            OrganizationUseCase organizationUseCase,
            CurrentUserOutputPort currentUserOutputPort
    ) {
        this.roomOutputPort = roomOutputPort;
        this.roomMapper = roomMapper;
        this.organizationUseCase = organizationUseCase;
        this.currentUserOutputPort = currentUserOutputPort;
    }

    @Override
    public List<Room> findAll() throws Exception {
        return roomOutputPort.findAll(currentUserOutputPort.getOrganizationId());
    }

    @Override
    public Optional<Room> findById(UUID roomId) throws Exception {
        return roomOutputPort.findByRoomIdAndOrganizationId(roomId, currentUserOutputPort.getOrganizationId());
    }

    @Override
    public Room save(CreateRoomReqDto reqDto) throws Exception {
        reqDto.validateSelf();

        var isHotelManagerOrRoom = currentUserOutputPort.hasAnyRole(allowedRoles);
        if (!isHotelManagerOrRoom) throw new ForbiddenException("not_allowed_to_create_rooms");

        var organizationId = currentUserOutputPort.getOrganizationId();
        var orgOpt = organizationUseCase.findById(organizationId);
        if (orgOpt.isEmpty()) throw new EntityNotFoundException("organization_not_found");

        var org = orgOpt.get();
        if (!org.getCatOrganizationType().is(CategoryEnum.OT_HOTEL))
            throw new ApplicationException("organization_is_not_a_hotel");

        var code = reqDto.getCode();
        var existsByCode = roomOutputPort.existsByCodeAndOrganizationIdAndRoomIdNot(code, organizationId, null);
        if (existsByCode) throw new ApplicationException("code_already_exists");

        var room = roomMapper.toRoom(reqDto);
        room.setRoomId(UUID.randomUUID());
        room.setOrganizationId(organizationId);
        return roomOutputPort.save(room);
    }

    @Override
    public Room update(UUID roomId, UpdateRoomReqDto reqDto) throws Exception {
        reqDto.validateSelf();

        var isHotelManagerOrRoom = currentUserOutputPort.hasAnyRole(allowedRoles);
        if (!isHotelManagerOrRoom) throw new ForbiddenException("not_allowed_to_create_rooms");

        var roomOpt = this.findById(roomId);
        if (roomOpt.isEmpty()) throw new EntityNotFoundException("room_not_found");

        var originalRoom = roomOpt.get();
        if (!originalRoom.getRoomId().equals(reqDto.getRoomId())) throw new ApplicationException("invalid_update");

        var code = reqDto.getCode();
        var existsByCode = roomOutputPort.existsByCodeAndOrganizationIdAndRoomIdNot(code, originalRoom.getOrganizationId(), roomId);
        if (existsByCode) throw new ApplicationException("code_already_exists");

        var room = roomMapper.toRoom(reqDto);
        room.setOrganizationId(originalRoom.getOrganizationId());
        return roomOutputPort.save(room);
    }
}

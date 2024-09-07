package com.cesar31.organization.application.ports.input;

import com.cesar31.organization.application.dto.CreateRoomReqDto;
import com.cesar31.organization.application.dto.UpdateRoomReqDto;
import com.cesar31.organization.domain.Room;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomUseCase {

    List<Room> findAll() throws Exception;

    Optional<Room> findById(UUID roomId) throws Exception;

    Room save(CreateRoomReqDto reqDto) throws Exception;

    Room update(UUID roomId, UpdateRoomReqDto reqDto) throws Exception;
}

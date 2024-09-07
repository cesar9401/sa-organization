package com.cesar31.organization.infrastructure.adapters.input.rest;

import com.cesar31.organization.application.dto.CreateRoomReqDto;
import com.cesar31.organization.application.dto.UpdateRoomReqDto;
import com.cesar31.organization.application.ports.input.RoomUseCase;
import com.cesar31.organization.domain.Room;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rooms")
public class RoomRestAdapter {

    private final RoomUseCase roomUseCase;

    public RoomRestAdapter(RoomUseCase roomUseCase) {
        this.roomUseCase = roomUseCase;
    }

    @GetMapping
    @Operation(description = "Find all the room of the organization the user belongs to.")
    public ResponseEntity<List<Room>> findAll() throws Exception {
        var rooms = roomUseCase.findAll();
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("{roomId}")
    @Operation(description = "Find a specific room of the organization the user belongs to.")
    public ResponseEntity<Room> findById(@PathVariable("roomId") UUID roomId) throws Exception {
        return roomUseCase.findById(roomId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(description = "Create a new room of the organization the user belongs to.")
    public ResponseEntity<Room> create(@RequestBody CreateRoomReqDto reqDto) throws Exception {
        var newRoom = roomUseCase.save(reqDto);
        return new ResponseEntity<>(newRoom, HttpStatus.CREATED);
    }

    @PutMapping("{roomId}")
    @Operation(description = "Update any room by its id of the organization the user belongs to.")
    public ResponseEntity<Room> update(@PathVariable("roomId") UUID roomId, @RequestBody UpdateRoomReqDto reqDto) throws Exception {
        var updatedRoom = roomUseCase.update(roomId, reqDto);
        return ResponseEntity.ok(updatedRoom);
    }
}

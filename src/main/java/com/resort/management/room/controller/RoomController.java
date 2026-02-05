package com.resort.management.room.controller;

import com.resort.management.room.dto.RoomRequest;
import com.resort.management.room.dto.RoomResponse;
import com.resort.management.room.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RoomController {

    private final RoomService roomService;

    @GetMapping
    public ResponseEntity<List<RoomResponse>> getAllRooms() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> getRoomById(@PathVariable Long id) {
        return ResponseEntity.ok(roomService.getRoomById(id));
    }

    @GetMapping("/number/{roomNumber}")
    public ResponseEntity<RoomResponse> getRoomByNumber(@PathVariable String roomNumber) {
        return ResponseEntity.ok(roomService.getRoomByRoomNumber(roomNumber));
    }

    @GetMapping("/status/{statusName}")
    public ResponseEntity<List<RoomResponse>> getRoomsByStatus(@PathVariable String statusName) {
        return ResponseEntity.ok(roomService.getRoomsByStatus(statusName));
    }

    @GetMapping("/type/{roomTypeId}")
    public ResponseEntity<List<RoomResponse>> getRoomsByType(@PathVariable Long roomTypeId) {
        return ResponseEntity.ok(roomService.getRoomsByType(roomTypeId));
    }

    @PostMapping
    public ResponseEntity<RoomResponse> createRoom(@Valid @RequestBody RoomRequest request) {
        RoomResponse response = roomService.createRoom(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomResponse> updateRoom(
            @PathVariable Long id,
            @Valid @RequestBody RoomRequest request) {
        return ResponseEntity.ok(roomService.updateRoom(id, request));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<RoomResponse> updateRoomStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return ResponseEntity.ok(roomService.updateRoomStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }
}
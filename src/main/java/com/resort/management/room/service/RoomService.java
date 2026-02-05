package com.resort.management.room.service;

import com.resort.management.room.dto.RoomRequest;
import com.resort.management.room.dto.RoomResponse;

import java.util.List;

public interface RoomService {

    List<RoomResponse> getAllRooms();

    RoomResponse getRoomById(Long id);

    RoomResponse getRoomByRoomNumber(String roomNumber);

    RoomResponse createRoom(RoomRequest request);

    RoomResponse updateRoom(Long id, RoomRequest request);

    void deleteRoom(Long id);

    RoomResponse updateRoomStatus(Long roomId, String statusName);

    List<RoomResponse> getRoomsByStatus(String statusName);

    List<RoomResponse> getRoomsByType(Long roomTypeId);
}
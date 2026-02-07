package com.resort.management.room.service;

import com.resort.management.room.dto.RoomTypeRequest;
import com.resort.management.room.dto.RoomTypeResponse;

import java.util.List;

public interface RoomTypeService {

    List<RoomTypeResponse> getAllRoomTypes();

    RoomTypeResponse getRoomTypeById(Long id);

    RoomTypeResponse getRoomTypeByName(String name);

    RoomTypeResponse createRoomType(RoomTypeRequest request);

    RoomTypeResponse updateRoomType(Long id, RoomTypeRequest request);

    void deleteRoomType(Long id);
}
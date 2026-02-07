package com.resort.management.room.service.impl;

import com.resort.management.room.dto.RoomRequest;
import com.resort.management.room.dto.RoomResponse;
import com.resort.management.room.entity.Room;
import com.resort.management.room.entity.RoomStatus;
import com.resort.management.room.entity.RoomType;
import com.resort.management.room.repository.RoomRepository;
import com.resort.management.room.repository.RoomStatusRepository;
import com.resort.management.room.repository.RoomTypeRepository;
import com.resort.management.room.service.RoomService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final RoomStatusRepository roomStatusRepository;

    @Override
    public List<RoomResponse> getAllRooms() {
        return roomRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RoomResponse getRoomById(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room not found with id: " + id));
        return mapToResponse(room);
    }

    @Override
    public RoomResponse getRoomByRoomNumber(String roomNumber) {
        Room room = roomRepository.findByRoomNumber(roomNumber)
                .orElseThrow(() -> new EntityNotFoundException("Room not found with number: " + roomNumber));
        return mapToResponse(room);
    }

    @Override
    public RoomResponse createRoom(RoomRequest request) {
        RoomType roomType = roomTypeRepository.findById(request.getRoomTypeId())
                .orElseThrow(() -> new EntityNotFoundException("RoomType not found"));

        RoomStatus defaultStatus = roomStatusRepository.findByName("AVAILABLE")
                .orElseThrow(() -> new EntityNotFoundException("Default status 'AVAILABLE' not found"));

        Room room = Room.builder()
                .roomNumber(request.getRoomNumber())
                .floor(request.getFloor())
                .building(request.getBuilding())
                .roomType(roomType)
                .roomStatus(defaultStatus)
                .isActive(true)
                .build();

        Room savedRoom = roomRepository.save(room);
        return mapToResponse(savedRoom);
    }

    @Override
    public RoomResponse updateRoom(Long id, RoomRequest request) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room not found"));

        RoomType roomType = roomTypeRepository.findById(request.getRoomTypeId())
                .orElseThrow(() -> new EntityNotFoundException("RoomType not found"));

        room.setRoomNumber(request.getRoomNumber());
        room.setFloor(request.getFloor());
        room.setBuilding(request.getBuilding());
        room.setRoomType(roomType);

        Room updatedRoom = roomRepository.save(room);
        return mapToResponse(updatedRoom);
    }

    @Override
    public void deleteRoom(Long id) {
        if (!roomRepository.existsById(id)) {
            throw new EntityNotFoundException("Room not found");
        }
        roomRepository.deleteById(id);
    }

    @Override
    public RoomResponse updateRoomStatus(Long roomId, String statusName) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new EntityNotFoundException("Room not found"));

        RoomStatus newStatus = roomStatusRepository.findByName(statusName.toUpperCase())
                .orElseThrow(() -> new EntityNotFoundException("Status not found: " + statusName));

        room.setRoomStatus(newStatus);
        Room updated = roomRepository.save(room);
        return mapToResponse(updated);
    }

    @Override
    public List<RoomResponse> getRoomsByStatus(String statusName) {
        return roomRepository.findByRoomStatus_Name(statusName.toUpperCase()).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomResponse> getRoomsByType(Long roomTypeId) {
        return roomRepository.findByRoomType_Id(roomTypeId, org.springframework.data.domain.Pageable.unpaged())
                .getContent().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private RoomResponse mapToResponse(Room room) {
        return RoomResponse.builder()
                .id(room.getId())
                .roomNumber(room.getRoomNumber())
                .floor(room.getFloor())
                .building(room.getBuilding())
                .roomTypeName(room.getRoomType().getName())
                .roomTypeId(room.getRoomType().getId())
                .statusName(room.getRoomStatus().getName())
                .statusId(room.getRoomStatus().getId())
                .isActive(room.getIsActive())
                .basePrice(room.getRoomType().getBasePrice())
                .maxOccupancy(room.getRoomType().getMaxOccupancy())
                .smokingAllowed(room.getRoomType().getSmokingAllowed())
                .hasBalcony(room.getRoomType().getHasBalcony())
                .hasSeaView(room.getRoomType().getHasSeaView())
                .build();
    }
}
package com.resort.management.room.service.impl;

import com.resort.management.room.dto.RoomTypeRequest;
import com.resort.management.room.dto.RoomTypeResponse;
import com.resort.management.room.entity.RoomType;
import com.resort.management.room.exception.ResourceNotFoundException;
import com.resort.management.room.repository.RoomTypeRepository;
import com.resort.management.room.service.RoomTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomTypeServiceImpl implements RoomTypeService {

    private final RoomTypeRepository roomTypeRepository;

    @Override
    public List<RoomTypeResponse> getAllRoomTypes() {
        return roomTypeRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RoomTypeResponse getRoomTypeById(Long id) {
        RoomType roomType = roomTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RoomType", "id", id));
        return mapToResponse(roomType);
    }

    @Override
    public RoomTypeResponse getRoomTypeByName(String name) {
        RoomType roomType = roomTypeRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("RoomType", "name", name));
        return mapToResponse(roomType);
    }

    @Override
    public RoomTypeResponse createRoomType(RoomTypeRequest request) {
        if (roomTypeRepository.existsByName(request.getName())) {
            throw new IllegalArgumentException("Room type with name '" + request.getName() + "' already exists");
        }

        RoomType roomType = RoomType.builder()
                .name(request.getName())
                .description(request.getDescription())
                .basePrice(request.getBasePrice())
                .maxOccupancy(request.getMaxOccupancy())
                .smokingAllowed(request.getSmokingAllowed())
                .hasBalcony(request.getHasBalcony())
                .hasSeaView(request.getHasSeaView())
                .build();

        RoomType saved = roomTypeRepository.save(roomType);
        return mapToResponse(saved);
    }

    @Override
    public RoomTypeResponse updateRoomType(Long id, RoomTypeRequest request) {
        RoomType roomType = roomTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RoomType", "id", id));

        // Only update if new name is different and not taken
        if (!roomType.getName().equals(request.getName()) &&
                roomTypeRepository.existsByName(request.getName())) {
            throw new IllegalArgumentException("Room type name '" + request.getName() + "' already exists");
        }

        roomType.setName(request.getName());
        roomType.setDescription(request.getDescription());
        roomType.setBasePrice(request.getBasePrice());
        roomType.setMaxOccupancy(request.getMaxOccupancy());
        roomType.setSmokingAllowed(request.getSmokingAllowed());
        roomType.setHasBalcony(request.getHasBalcony());
        roomType.setHasSeaView(request.getHasSeaView());

        RoomType updated = roomTypeRepository.save(roomType);
        return mapToResponse(updated);
    }

    @Override
    public void deleteRoomType(Long id) {
        if (!roomTypeRepository.existsById(id)) {
            throw new ResourceNotFoundException("RoomType", "id", id);
        }
        roomTypeRepository.deleteById(id);
    }

    private RoomTypeResponse mapToResponse(RoomType roomType) {
        return RoomTypeResponse.builder()
                .id(roomType.getId())
                .name(roomType.getName())
                .description(roomType.getDescription())
                .basePrice(roomType.getBasePrice())
                .maxOccupancy(roomType.getMaxOccupancy())
                .smokingAllowed(roomType.getSmokingAllowed())
                .hasBalcony(roomType.getHasBalcony())
                .hasSeaView(roomType.getHasSeaView())
                .build();
    }
}
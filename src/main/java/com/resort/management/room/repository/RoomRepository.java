package com.resort.management.room.repository;

import com.resort.management.room.entity.Room;
import com.resort.management.room.entity.RoomStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findByRoomNumber(String roomNumber);

    List<Room> findByRoomStatus(RoomStatus roomStatus);

    List<Room> findByRoomStatus_Name(String statusName);

    Page<Room> findByRoomType_Id(Long roomTypeId, Pageable pageable);

    Page<Room> findByRoomStatus_Name(String statusName, Pageable pageable);

    boolean existsByRoomNumber(String roomNumber);

    List<Room> findByBuilding(String building);

    List<Room> findByFloor(Integer floor);
}
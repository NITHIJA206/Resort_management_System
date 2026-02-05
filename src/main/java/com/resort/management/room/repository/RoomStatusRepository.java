package com.resort.management.room.repository;

import com.resort.management.room.entity.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomStatusRepository extends JpaRepository<RoomStatus, Long> {

    Optional<RoomStatus> findByName(String name);

    boolean existsByName(String name);
}
package com.resort.management.repository;

import com.resort.management.entity.booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface bookingRepository extends JpaRepository<booking, Long> {

    List<booking> findByUser_UserId(Long userId);

    // Overlap rule:
    // existing.checkIn < newCheckOut AND existing.checkOut > newCheckIn
    @Query("""
        SELECT COUNT(b) > 0
        FROM booking b
        WHERE b.room.roomId = :roomId
          AND b.status IN ('PENDING','CONFIRMED')
          AND b.checkInDate < :newCheckOut
          AND b.checkOutDate > :newCheckIn
    """)
    boolean existsOverlappingBooking(@Param("roomId") Long roomId,
                                     @Param("newCheckIn") LocalDate newCheckIn,
                                     @Param("newCheckOut") LocalDate newCheckOut);
}

package com.resort.management.service;

import com.resort.management.dto.bookingCreateRequest;
import com.resort.management.dto.bookingStatusUpdateRequest;
import com.resort.management.entity.booking;
import com.resort.management.entity.room;
import com.resort.management.entity.user;
import com.resort.management.exception.badRequestException;
import com.resort.management.exception.notFoundException;
import com.resort.management.repository.bookingRepository;
import com.resort.management.repository.roomRepository;
import com.resort.management.repository.userRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class bookingService {

    private final bookingRepository bookingRepository;
    private final userRepository userRepository;
    private final roomRepository roomRepository;

    public bookingService(bookingRepository bookingRepository,
                          userRepository userRepository,
                          roomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }

    @Transactional
    public booking createBooking(bookingCreateRequest req) {

        if (!req.getCheckOutDate().isAfter(req.getCheckInDate())) {
            throw new badRequestException("checkOutDate must be after checkInDate");
        }

        user user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new notFoundException("User not found"));

        room room = roomRepository.findById(req.getRoomId())
                .orElseThrow(() -> new notFoundException("Room not found"));

        boolean overlap = bookingRepository.existsOverlappingBooking(
                room.getRoomId(),
                req.getCheckInDate(),
                req.getCheckOutDate()
        );

        if (overlap) {
            throw new badRequestException("Room already booked for these dates");
        }

        long nights = ChronoUnit.DAYS.between(
                req.getCheckInDate(),
                req.getCheckOutDate()
        );

        double totalAmount = nights * room.getPricePerNight();

        // ✅ CORRECT: initialize booking immediately
        booking booking = com.resort.management.entity.booking.builder()
                .user(user)
                .room(room)
                .checkInDate(req.getCheckInDate())
                .checkOutDate(req.getCheckOutDate())
                .totalAmount(totalAmount)
                .status("PENDING")
                .createdAt(LocalDateTime.now())
                .build();

        return bookingRepository.save(booking);
    }


    public booking getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new notFoundException("Booking not found: " + bookingId));
    }

    public List<booking> getBookingsByUser(Long userId) {
        return bookingRepository.findByUser_UserId(userId);
    }

    public List<booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Transactional
    public booking updateBookingStatus(Long bookingId, bookingStatusUpdateRequest req) {
        booking booking = getBookingById(bookingId);

        String newStatus = req.getStatus().toUpperCase().trim();
        if (!List.of("CONFIRMED", "CANCELLED").contains(newStatus)) {
            throw new badRequestException("Status must be CONFIRMED or CANCELLED");
        }

        booking.setStatus(newStatus);
        return bookingRepository.save(booking);
    }

    @Transactional
    public void cancelBooking(Long bookingId) {
        booking booking = getBookingById(bookingId);
        booking.setStatus("CANCELLED");
        bookingRepository.save(booking);
    }

    @Transactional
    public void deleteBooking(Long bookingId) {
        booking booking = getBookingById(bookingId);
        bookingRepository.delete(booking);
    }
}

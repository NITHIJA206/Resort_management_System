package com.resort.management.controller;

import com.resort.management.dto.bookingCreateRequest;
import com.resort.management.dto.bookingStatusUpdateRequest;
import com.resort.management.entity.booking;
import com.resort.management.service.bookingService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class bookingController {

    private final bookingService bookingService;

    public bookingController(bookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public booking createBooking(@Valid @RequestBody bookingCreateRequest request) {
        return bookingService.createBooking(request);
    }

    @GetMapping("/{id}")
    public booking getBooking(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    @GetMapping("/user/{userId}")
    public List<booking> getBookingsByUser(@PathVariable Long userId) {
        return bookingService.getBookingsByUser(userId);
    }

    @GetMapping
    public List<booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @PutMapping("/{id}/status")
    public booking updateStatus(@PathVariable Long id,
                                @Valid @RequestBody bookingStatusUpdateRequest request) {
        return bookingService.updateBookingStatus(id, request);
    }

    @PutMapping("/{id}/cancel")
    public String cancel(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return "Booking cancelled successfully";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return "Booking deleted successfully";
    }
}

package com.resort.management.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private user user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "room_id")
    private room room;

    @Column(nullable = false)
    private LocalDate checkInDate;

    @Column(nullable = false)
    private LocalDate checkOutDate;

    @Column(nullable = false)
    private double totalAmount;

    @Column(nullable = false)
    private String status; // PENDING / CONFIRMED / CANCELLED

    @Column(nullable = false)
    private LocalDateTime createdAt;
}

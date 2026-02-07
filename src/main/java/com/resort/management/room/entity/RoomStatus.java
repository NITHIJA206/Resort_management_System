package com.resort.management.room.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "room_status")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;                    // AVAILABLE, OCCUPIED, MAINTENANCE, CLEANING, OUT_OF_SERVICE, RESERVED

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable = true;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
}
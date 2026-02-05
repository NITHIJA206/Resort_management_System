package com.resort.management.room.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "room_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;                    // e.g., Deluxe, Suite, Ocean View, Standard

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal basePrice;

    @Column(name = "max_occupancy")
    private Integer maxOccupancy;

    @Column(name = "smoking_allowed")
    private Boolean smokingAllowed = false;

    @Column(name = "has_balcony")
    private Boolean hasBalcony = false;

    @Column(name = "has_sea_view")
    private Boolean hasSeaView = false;
}
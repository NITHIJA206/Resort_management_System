package com.resort.management.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rooms")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @Column(nullable = false, unique = true)
    private String roomNumber;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private double pricePerNight;

    @Column(nullable = false)
    private int capacity;

    @Column(nullable = false)
    private String status; // AVAILABLE / MAINTENANCE

    @Column(length = 500)
    private String description;
}

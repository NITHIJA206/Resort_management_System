package com.resort.management.room.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomTypeResponse {

    private Long id;
    private String name;
    private String description;
    private BigDecimal basePrice;
    private Integer maxOccupancy;
    private Boolean smokingAllowed;
    private Boolean hasBalcony;
    private Boolean hasSeaView;
}
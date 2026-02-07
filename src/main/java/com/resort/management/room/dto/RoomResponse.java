package com.resort.management.room.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomResponse {

    private Long id;
    private String roomNumber;
    private Integer floor;
    private String building;
    private String roomTypeName;
    private Long roomTypeId;
    private String statusName;
    private Long statusId;
    private Boolean isActive;
    private BigDecimal basePrice;
    private Integer maxOccupancy;
    private Boolean smokingAllowed;
    private Boolean hasBalcony;
    private Boolean hasSeaView;
}
package com.resort.management.room.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomRequest {

    @NotBlank(message = "Room number is required")
    @Size(min = 2, max = 20, message = "Room number must be between 2-20 characters")
    private String roomNumber;

    @NotNull(message = "Floor is required")
    @Min(value = 1, message = "Floor must be at least 1")
    private Integer floor;

    private String building;

    @NotNull(message = "Room type ID is required")
    private Long roomTypeId;

    // Optional fields
    private Boolean smokingAllowed;
    private Boolean hasBalcony;
    private Boolean hasSeaView;
}
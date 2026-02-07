package com.resort.management.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class bookingStatusUpdateRequest {

    @NotBlank
    private String status; // CONFIRMED / CANCELLED
}

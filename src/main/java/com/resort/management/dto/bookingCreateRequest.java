package com.resort.management.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class bookingCreateRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long roomId;

    @NotNull @Future
    private LocalDate checkInDate;

    @NotNull @Future
    private LocalDate checkOutDate;
}

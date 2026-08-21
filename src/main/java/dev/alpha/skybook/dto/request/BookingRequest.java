package dev.alpha.skybook.dto.request;

import dev.alpha.skybook.enums.BookingStatus;
import jakarta.validation.constraints.NotNull;

public record BookingRequest(
        @NotNull
        Long passengerId,
        @NotNull
        Long flightId,
        @NotNull
        BookingStatus status
) {
}
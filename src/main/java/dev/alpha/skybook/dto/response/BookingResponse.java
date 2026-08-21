package dev.alpha.skybook.dto.response;

import dev.alpha.skybook.enums.BookingStatus;

public record BookingResponse(
        Long id,
        Long passengerId,
        Long flightId,
        BookingStatus status
) {
}
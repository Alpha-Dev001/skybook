package dev.alpha.skybook.dto.request;

import dev.alpha.skybook.enums.FlightStatus;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public record FlightRequest(
        @NotBlank String flightNumber,
        @NotNull Long aircraftId,
        @NotNull Long departureAirportId,
        @NotNull Long arrivalAirportId,
        @NotNull LocalDateTime departureTime,
        @NotNull LocalDateTime arrivalTime,
        @NotNull @DecimalMin("0.0") BigDecimal price,
        @NotNull FlightStatus status
) {
}
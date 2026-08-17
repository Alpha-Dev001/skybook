package dev.alpha.skybook.dto.response;

import dev.alpha.skybook.enums.FlightStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public record FlightResponse(
        Long id,
        String flightNumber,
        String aircraftRegistration,
        String departureAirport,
        String arrivalAirport,
        LocalDateTime departureTime,
        LocalDateTime arrivalTime,
        BigDecimal price,
        FlightStatus status
) {
}
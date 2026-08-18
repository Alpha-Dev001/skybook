package dev.alpha.skybook.mapper;

import dev.alpha.skybook.dto.response.FlightResponse;
import dev.alpha.skybook.entity.Flight;

public class FlightMapper {

    private FlightMapper() {
    }
    public static FlightResponse toResponse(Flight flight) {
        return new FlightResponse(
                flight.getId(),
                flight.getFlightNumber(),
                flight.getAircraft().getRegistrationNumber(),
                flight.getDepartureAirport().getName(),
                flight.getArrivalAirport().getName(),
                flight.getDepartureTime(),
                flight.getArrivalTime(),
                flight.getPrice(),
                flight.getStatus()
        );
    }
}
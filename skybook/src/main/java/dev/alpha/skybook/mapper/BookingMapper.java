package dev.alpha.skybook.mapper;

import dev.alpha.skybook.dto.request.BookingRequest;
import dev.alpha.skybook.dto.response.BookingResponse;
import dev.alpha.skybook.entity.Booking;
import dev.alpha.skybook.entity.Flight;
import dev.alpha.skybook.entity.Passenger;

public class BookingMapper {

    private BookingMapper() {
    }

    // Request DTO → Entity
    public static Booking toEntity(
            BookingRequest request,
            Passenger passenger,
            Flight flight
    ) {

        Booking booking = new Booking();

        booking.setPassenger(passenger);
        booking.setFlight(flight);
        booking.setStatus(request.status());

        return booking;
    }

    // Entity → Response DTO
    public static BookingResponse toResponse(
            Booking booking
    ) {

        return new BookingResponse(
                booking.getId(),
                booking.getPassenger().getId(),
                booking.getFlight().getId(),
                booking.getStatus()
        );
    }

    // Update existing Entity from Request DTO
    public static void updateEntity(
            Booking booking,
            BookingRequest request,
            Passenger passenger,
            Flight flight
    ) {

        booking.setPassenger(passenger);
        booking.setFlight(flight);
        booking.setStatus(request.status());
    }
}
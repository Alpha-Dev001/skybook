package dev.alpha.skybook.mapper;

import dev.alpha.skybook.dto.request.PassengerRequest;
import dev.alpha.skybook.dto.response.PassengerResponse;
import dev.alpha.skybook.entity.Passenger;

public class PassengerMapper {

    private PassengerMapper() {
    }

    // Request DTO → Entity
    public static Passenger toEntity(PassengerRequest request) {

        Passenger passenger = new Passenger();

        passenger.setFirstName(request.firstName());
        passenger.setLastName(request.lastName());
        passenger.setEmail(request.email());
        passenger.setPhone(request.phone());
        passenger.setPassportNumber(request.passportNumber());
        passenger.setDateOfBirth(request.dateOfBirth());
        passenger.setStatus(request.status());

        return passenger;
    }

    // Entity → Response DTO
    public static PassengerResponse toResponse(
            Passenger passenger
    ) {

        return new PassengerResponse(
                passenger.getId(),
                passenger.getFirstName(),
                passenger.getLastName(),
                passenger.getEmail(),
                passenger.getPhone(),
                passenger.getPassportNumber(),
                passenger.getDateOfBirth(),
                passenger.getStatus()
        );
    }

    // Update existing Entity from Request DTO
    public static void updateEntity(
            Passenger passenger,
            PassengerRequest request
    ) {

        passenger.setFirstName(request.firstName());
        passenger.setLastName(request.lastName());
        passenger.setEmail(request.email());
        passenger.setPhone(request.phone());
        passenger.setPassportNumber(request.passportNumber());
        passenger.setDateOfBirth(request.dateOfBirth());
        passenger.setStatus(request.status());
    }
}
package dev.alpha.skybook.mapper;

import dev.alpha.skybook.dto.response.PassengerResponse;
import dev.alpha.skybook.entity.Passenger;

public class PassengerMapper {

    private PassengerMapper() {
    }

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
}
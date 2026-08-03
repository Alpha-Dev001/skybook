package dev.alpha.skybook.dto.response;

import dev.alpha.skybook.enums.AircraftStatus;

public record AircraftResponse(

        Long id,

        String registrationNumber,

        String manufacturer,

        String model,

        Integer capacity,

        Integer manufactureYear,

        AircraftStatus status

) {
}
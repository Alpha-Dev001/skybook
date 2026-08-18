package dev.alpha.skybook.mapper;

import dev.alpha.skybook.dto.request.AircraftRequest;
import dev.alpha.skybook.dto.response.AircraftResponse;
import dev.alpha.skybook.entity.Aircraft;

public class AircraftMapper {
    private AircraftMapper() {
    }
    public static Aircraft toEntity(AircraftRequest request) {
        Aircraft aircraft = new Aircraft();
        aircraft.setRegistrationNumber(request.registrationNumber());
        aircraft.setManufacturer(request.manufacturer());
        aircraft.setModel(request.model());
        aircraft.setCapacity(request.capacity());
        aircraft.setManufactureYear(request.manufactureYear());
        aircraft.setStatus(request.status());
        return aircraft;
    }

    public static AircraftResponse toResponse(Aircraft aircraft) {
        return new AircraftResponse(
                aircraft.getId(),
                aircraft.getRegistrationNumber(),
                aircraft.getManufacturer(),
                aircraft.getModel(),
                aircraft.getCapacity(),
                aircraft.getManufactureYear(),
                aircraft.getStatus()
        );
    }
}
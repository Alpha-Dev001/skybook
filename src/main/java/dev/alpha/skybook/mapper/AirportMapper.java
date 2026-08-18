package dev.alpha.skybook.mapper;

import dev.alpha.skybook.dto.request.AirportRequest;
import dev.alpha.skybook.dto.response.AirportResponse;
import dev.alpha.skybook.entity.Airport;

public class AirportMapper {
    public static Airport toEntity(AirportRequest request){
        Airport airport = new Airport();
        airport.setCode(request.code());
        airport.setName(request.name());
        airport.setCity(request.city());
        airport.setCountry(request.country());
        return airport;
    }
    public static AirportResponse toResponse(Airport airport){
        return new AirportResponse(
            airport.getId(),
            airport.getCode(),
            airport.getName(),
            airport.getCity(),
            airport.getCountry()
        );
    }
}

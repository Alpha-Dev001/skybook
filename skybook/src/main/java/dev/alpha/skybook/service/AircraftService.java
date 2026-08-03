package dev.alpha.skybook.service;

import dev.alpha.skybook.dto.request.AircraftRequest;
import dev.alpha.skybook.dto.response.AircraftResponse;

import java.util.List;

public interface AircraftService {
    AircraftResponse createAircraft(AircraftRequest request);
    List<AircraftResponse> getAllAircraft();
    AircraftResponse getAircraftById(Long id);
    AircraftResponse updateAircraft(Long id, AircraftRequest request);
    void deleteAircraft(Long id);
}

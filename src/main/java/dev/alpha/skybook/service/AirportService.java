package dev.alpha.skybook.service;

import dev.alpha.skybook.dto.request.AirportRequest;
import dev.alpha.skybook.dto.response.AirportResponse;

import java.util.List;

public interface AirportService {
    AirportResponse createAirport(AirportRequest request);
    List<AirportResponse> getAllAirports();
    AirportResponse getAirportById(Long id);
    AirportResponse updateAirport(Long id, AirportRequest request);
    void deleteAirport(Long id);
}
package dev.alpha.skybook.service;

import dev.alpha.skybook.dto.request.FlightRequest;
import dev.alpha.skybook.dto.response.FlightResponse;

import java.util.List;

public interface FlightService {

    FlightResponse createFlight(FlightRequest request);

    List<FlightResponse> getAllFlights();

    FlightResponse getFlightById(Long id);

    FlightResponse updateFlight(Long id, FlightRequest request);

    void deleteFlight(Long id);
}
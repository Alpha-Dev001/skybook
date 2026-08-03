package dev.alpha.skybook.controller;

import dev.alpha.skybook.common.ApiResponse;
import dev.alpha.skybook.dto.request.AirportRequest;
import dev.alpha.skybook.dto.response.AirportResponse;
import dev.alpha.skybook.service.AirportService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<AirportResponse> createAirport(
            @Valid @RequestBody AirportRequest request
    ) {

        return ApiResponse.success(
                "Airport created successfully",
                airportService.createAirport(request)
        );
    }

    @GetMapping
    public ApiResponse<List<AirportResponse>> getAllAirports() {

        return ApiResponse.success(
                "Airports retrieved successfully",
                airportService.getAllAirports()
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<AirportResponse> getAirportById(
            @PathVariable Long id
    ) {

        return ApiResponse.success(
                "Airport retrieved successfully",
                airportService.getAirportById(id)
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<AirportResponse> updateAirport(
            @PathVariable Long id,
            @Valid @RequestBody AirportRequest request
    ) {

        return ApiResponse.success(
                "Airport updated successfully",
                airportService.updateAirport(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteAirport(
            @PathVariable Long id
    ) {

        airportService.deleteAirport(id);

        return ApiResponse.success(
                "Airport deleted successfully",
                null
        );
    }

}
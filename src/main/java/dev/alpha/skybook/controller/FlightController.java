package dev.alpha.skybook.controller;

import dev.alpha.skybook.dto.request.FlightRequest;
import dev.alpha.skybook.dto.response.FlightResponse;
import dev.alpha.skybook.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {
    private final FlightService flightService;
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }
    @PostMapping
    public ResponseEntity<FlightResponse> createFlight(@Valid @RequestBody FlightRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(flightService.createFlight(request));
    }

    @GetMapping
    public ResponseEntity<List<FlightResponse>> getAllFlights() {
        return ResponseEntity.ok(
                flightService.getAllFlights()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightResponse> getFlightById(@PathVariable Long id) {
        return ResponseEntity.ok(
                flightService.getFlightById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightResponse> updateFlight(@PathVariable Long id,@Valid @RequestBody FlightRequest request) {
        return ResponseEntity.ok(flightService.updateFlight(id, request) );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight( @PathVariable Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }
}
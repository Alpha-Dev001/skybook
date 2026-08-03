package dev.alpha.skybook.controller;

import dev.alpha.skybook.dto.request.AircraftRequest;
import dev.alpha.skybook.dto.response.AircraftResponse;
import dev.alpha.skybook.service.AircraftService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/aircraft")
public class AircraftController {


    private final AircraftService aircraftService;


    public AircraftController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }


    @PostMapping
    public ResponseEntity<AircraftResponse> createAircraft(
            @Valid @RequestBody AircraftRequest request
    ){

        AircraftResponse response =
                aircraftService.createAircraft(request);

        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED
        );
    }


    @GetMapping
    public ResponseEntity<List<AircraftResponse>> getAllAircraft(){

        return ResponseEntity.ok(
                aircraftService.getAllAircraft()
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<AircraftResponse> getAircraftById(
            @PathVariable Long id
    ){

        return ResponseEntity.ok(
                aircraftService.getAircraftById(id)
        );
    }


    @PutMapping("/{id}")
    public ResponseEntity<AircraftResponse> updateAircraft(
            @PathVariable Long id,
            @Valid @RequestBody AircraftRequest request
    ){

        return ResponseEntity.ok(
                aircraftService.updateAircraft(id, request)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAircraft(
            @PathVariable Long id
    ){

        aircraftService.deleteAircraft(id);

        return ResponseEntity.noContent().build();
    }

}
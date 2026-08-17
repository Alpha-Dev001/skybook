package dev.alpha.skybook.controller;

import dev.alpha.skybook.dto.request.PassengerRequest;
import dev.alpha.skybook.dto.response.PassengerResponse;
import dev.alpha.skybook.service.PassengerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @PostMapping
    public ResponseEntity<PassengerResponse> createPassenger(
            @Valid @RequestBody PassengerRequest request
    ) {

        PassengerResponse response =
                passengerService.createPassenger(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PassengerResponse> getPassengerById(
            @PathVariable Long id
    ) {

        PassengerResponse response =
                passengerService.getPassengerById(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<PassengerResponse>> getAllPassengers() {

        List<PassengerResponse> responses =
                passengerService.getAllPassengers();

        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PassengerResponse> updatePassenger(
            @PathVariable Long id,
            @Valid @RequestBody PassengerRequest request
    ) {

        PassengerResponse response =
                passengerService.updatePassenger(id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePassenger(
            @PathVariable Long id
    ) {

        passengerService.deletePassenger(id);

        return ResponseEntity.noContent().build();
    }
}
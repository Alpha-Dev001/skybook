package dev.alpha.skybook.dto.request;

import dev.alpha.skybook.enums.AircraftStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AircraftRequest(
        @NotBlank(message = "Registration number is required")
        String registrationNumber,

        @NotBlank(message = "Manufacturer is required")
        String manufacturer,

        @NotBlank(message = "Model is required")
        String model,

        @NotNull(message = "Capacity is required")
        @Min(value = 1, message = "Capacity must be greater than 0")
        Integer capacity,

        @NotNull(message = "Manufacture year is required")
        @Min(value = 1950, message = "Invalid manufacture year")
        @Max(value = 2100, message = "Invalid manufacture year")
        Integer manufactureYear,

        @NotNull(message = "Status is required")
        AircraftStatus status
) {
}
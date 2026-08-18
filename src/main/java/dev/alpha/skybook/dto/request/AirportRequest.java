package dev.alpha.skybook.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AirportRequest(
        @NotBlank(message = "Airport code is required")
        @Size(min = 3, max = 3,message = "Airport code must have 3 characters")
        String code,

        @NotBlank(message = "Airport name is required")
        @Size(min = 3, max = 100,message = "Airport name length is invalid")
        String name,

        @NotBlank(message = "City is required")
        String city,

        @NotBlank(message = "Country is required")
        String country
) {}
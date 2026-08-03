package dev.alpha.skybook.dto.response;

public record AirportResponse(
    Long id,
    String code,
    String name,
    String city,
    String country
) {}

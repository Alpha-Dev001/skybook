package dev.alpha.skybook.dto.response;

import java.time.LocalDate;
import dev.alpha.skybook.enums.PassengerStatus;

public record PassengerResponse(
    Long id,
    String firstName,
    String lastName,
    String email,
    String phone,
    String passportNumber,
    LocalDate dateOFBirth,
    PassengerStatus status
) {
}

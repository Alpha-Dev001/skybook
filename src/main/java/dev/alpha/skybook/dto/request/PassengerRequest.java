package dev.alpha.skybook.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

import dev.alpha.skybook.enums.PassengerStatus;

public record PassengerRequest(
    @NotBlank String firstName,
    @NotBlank String lastName,
    @NotBlank @Email String email,
    @NotBlank String phone,
    @NotBlank String passportNumber,
    @NotNull LocalDate dateOfBirth,
    @NotNull PassengerStatus status
){}

package dev.alpha.skybook.exception;

import dev.alpha.skybook.common.ErrorResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // =========================
    // Airport Exceptions
    // =========================

    @ExceptionHandler(AirportAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleAirportAlreadyExists(
            AirportAlreadyExistsException ex
    ) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        ErrorResponse.of(
                                ex.getMessage(),
                                List.of()
                        )
                );
    }

    @ExceptionHandler(AirportNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAirportNotFound(
            AirportNotFoundException ex
    ) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorResponse.of(
                                ex.getMessage(),
                                List.of()
                        )
                );
    }

    // =========================
    // Aircraft Exceptions
    // =========================

    @ExceptionHandler(AircraftAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleAircraftAlreadyExists(
            AircraftAlreadyExistsException ex
    ) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        ErrorResponse.of(
                                ex.getMessage(),
                                List.of()
                        )
                );
    }

    @ExceptionHandler(AircraftNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAircraftNotFound(
            AircraftNotFoundException ex
    ) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorResponse.of(
                                ex.getMessage(),
                                List.of()
                        )
                );
    }

    // =========================
    // Validation Exceptions
    // =========================

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException ex
    ) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .toList();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorResponse.of(
                                "Validation failed",
                                errors
                        )
                );
    }

    // =========================
    // Generic Exception
    // =========================

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(
            Exception ex
    ) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ErrorResponse.of(
                                "Unexpected server error",
                                List.of(ex.getMessage())
                        )
                );
    }

}
package dev.alpha.skybook.exception;

public class FlightAlreadyExistsException extends RuntimeException {

    public FlightAlreadyExistsException(String message) {
        super(message);
    }
}
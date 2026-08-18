package dev.alpha.skybook.exception;

public class AirportNotFoundException extends RuntimeException {
    public AirportNotFoundException(String message) {
        super(message);
    }
}
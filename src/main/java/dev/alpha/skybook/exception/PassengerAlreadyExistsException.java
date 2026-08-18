package dev.alpha.skybook.exception;

public class PassengerAlreadyExistsException extends RuntimeException {
    public PassengerAlreadyExistsException(String message) {
        super(message);
    }
}
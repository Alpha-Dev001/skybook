package dev.alpha.skybook.exception;

public class PassengerNotFoundException extends RuntimeException {
    public PassengerNotFoundException(String message) {
        super(message);
    }
}
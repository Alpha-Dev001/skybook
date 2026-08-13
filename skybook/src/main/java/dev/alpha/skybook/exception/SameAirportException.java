package dev.alpha.skybook.exception;

public class SameAirportException extends RuntimeException {

    public SameAirportException(String message) {
        super(message);
    }
}
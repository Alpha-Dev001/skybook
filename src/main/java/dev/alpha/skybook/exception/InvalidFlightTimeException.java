package dev.alpha.skybook.exception;

public class InvalidFlightTimeException extends RuntimeException {

    public InvalidFlightTimeException(String message) {
        super(message);
    }
}
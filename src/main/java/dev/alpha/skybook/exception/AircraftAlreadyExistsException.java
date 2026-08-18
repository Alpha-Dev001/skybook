package dev.alpha.skybook.exception;

public class AircraftAlreadyExistsException extends RuntimeException{
    public AircraftAlreadyExistsException(String message){
        super(message);
    }
}

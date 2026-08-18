package dev.alpha.skybook.exception;

public class AircraftNotFoundException extends RuntimeException{
    public AircraftNotFoundException(String message){
        super(message);
    }    
}

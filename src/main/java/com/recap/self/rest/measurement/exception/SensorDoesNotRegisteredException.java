package com.recap.self.rest.measurement.exception;

public class SensorDoesNotRegisteredException extends RuntimeException{

    public SensorDoesNotRegisteredException(String message) {
        super(message);
    }
}

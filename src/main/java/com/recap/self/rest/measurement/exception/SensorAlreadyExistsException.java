package com.recap.self.rest.measurement.exception;

public class SensorAlreadyExistsException extends RuntimeException {

    public SensorAlreadyExistsException(String message) {
        super(message);
    }
}

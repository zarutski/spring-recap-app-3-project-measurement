package com.recap.self.rest.measurement.exception.handler;

import com.recap.self.rest.measurement.exception.ModelValidationException;
import com.recap.self.rest.measurement.exception.SensorAlreadyExistsException;
import com.recap.self.rest.measurement.exception.response.InternalErrorResponse;
import com.recap.self.rest.measurement.exception.SensorDoesNotRegisteredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<InternalErrorResponse> handleAlreadyExists(SensorAlreadyExistsException e) {
        return new ResponseEntity<>(new InternalErrorResponse(e.getMessage(), System.currentTimeMillis()), HttpStatus.CONFLICT);
    }


    @ExceptionHandler
    public ResponseEntity<InternalErrorResponse> handleModelValidationExists(ModelValidationException e) {
        return new ResponseEntity<>(new InternalErrorResponse(e.getMessage(), System.currentTimeMillis()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<InternalErrorResponse> handleAlreadyExists(SensorDoesNotRegisteredException e) {
        return new ResponseEntity<>(new InternalErrorResponse(e.getMessage(), System.currentTimeMillis()), HttpStatus.NOT_FOUND);
    }
}

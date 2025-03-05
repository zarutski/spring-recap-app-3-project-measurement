package com.recap.self.rest.measurement.exception;

import org.springframework.validation.FieldError;

import java.util.List;

public class ValidationExceptionBuilder {

    private String message;

    public ValidationExceptionBuilder addErrors() {
        return this;
    }

    public ModelValidationException build() {
        return new ModelValidationException(message);
    }

    public static ValidationExceptionBuilder builder() {
        return new ValidationExceptionBuilder();
    }

    public ValidationExceptionBuilder addErrors(List<FieldError> fieldErrors) {
        StringBuilder errorMessage = new StringBuilder();
        for (FieldError error : fieldErrors) {
            errorMessage.append(error.getField())
                    .append(" - ")
                    .append(error.getDefaultMessage())
                    .append(";");
        }
        this.message = errorMessage.toString();
        return this;
    }

}

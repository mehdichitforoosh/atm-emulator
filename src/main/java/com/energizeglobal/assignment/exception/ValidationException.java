package com.energizeglobal.assignment.exception;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 */
public class ValidationException extends RuntimeException {

    private final Errors errors;
    private final List<FieldError> fieldErrors;

    public ValidationException(String objectName, String field, String code) {
        this.errors = null;
        this.fieldErrors = new ArrayList<>();
        addErrorCode(objectName, field, code);
    }

    public ValidationException() {
        this.errors = null;
        this.fieldErrors = new ArrayList<>();
    }

    public ValidationException(Errors errors) {
        this.errors = errors;
        this.fieldErrors = Collections.emptyList();
    }

    public Errors getErrors() {
        return errors;
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    public void addErrorCode(String objectName, String field, String code) {
        String[] codes = new String[]{code};
        FieldError fieldError = new FieldError(objectName, field, null, true, codes, null, null);
        fieldErrors.add(fieldError);
    }

    public void addErrorCode(String objectName, String field, String defaultMessage, String code) {
        String[] codes = new String[]{code};
        FieldError fieldError = new FieldError(objectName, field, null, true, codes, null, defaultMessage);
        fieldErrors.add(fieldError);
    }
}

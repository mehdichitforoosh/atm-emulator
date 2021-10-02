package com.energizeglobal.assignment.common.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"code", "message", "errors"})
public class ErrorJsonObject {

    @JsonProperty(value = "code", required = true)
    protected Integer code;
    @JsonProperty(value = "message", required = true)
    protected String message;
    @JsonProperty(value = "errors", required = true)
    protected List<ErrorArrayJsonObject> errors;

    @JsonCreator
    public ErrorJsonObject(@JsonProperty(value = "code") Integer code, @JsonProperty(value = "message") String message) {
        this.code = code;
        this.message = message;
    }

    @JsonCreator
    public ErrorJsonObject(@JsonProperty(value = "code") Integer code, @JsonProperty(value = "message") String message, @JsonProperty(value = "errors") List<ErrorArrayJsonObject> errors) {
        this.code = code;
        this.message = message;
        this.errors = errors;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ErrorArrayJsonObject> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorArrayJsonObject> errors) {
        this.errors = errors;
    }
}

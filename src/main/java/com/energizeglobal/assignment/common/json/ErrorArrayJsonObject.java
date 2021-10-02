package com.energizeglobal.assignment.common.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"domain", "reason", "message", "location", "locationType", "extendedHelp", "sendReport"})
public class ErrorArrayJsonObject {


    @JsonProperty(value = "domain", required = true)
    protected String domain;
    @JsonProperty(value = "field", required = true)
    protected String field;
    @JsonProperty(value = "reason", required = true)
    protected String reason;
    @JsonProperty(value = "message", required = true)
    protected String message;
    @JsonProperty(value = "location", required = true)
    protected String location;
    @JsonProperty(value = "locationType", required = true)
    protected String locationType;
    @JsonProperty(value = "extendedHelp", required = true)
    protected String extendedHelp;
    @JsonProperty(value = "sendReport", required = true)
    protected String sendReport;

    @JsonCreator
    public ErrorArrayJsonObject(@JsonProperty(value = "reason") String reason, @JsonProperty(value = "message") String message) {
        this.reason = reason;
        this.message = message;
    }

    @JsonCreator
    public ErrorArrayJsonObject(@JsonProperty(value = "domain") String domain
            , @JsonProperty(value = "reason") String reason
            , @JsonProperty(value = "message") String message) {
        this.domain = domain;
        this.reason = reason;
        this.message = message;
    }

    @JsonCreator
    public ErrorArrayJsonObject(@JsonProperty(value = "domain") String domain
            , @JsonProperty(value = "field") String field
            , @JsonProperty(value = "reason") String reason
            , @JsonProperty(value = "message") String message) {
        this.domain = domain;
        this.field = field;
        this.reason = reason;
        this.message = message;
    }

    @JsonCreator
    public ErrorArrayJsonObject(@JsonProperty(value = "domain") String domain
            , @JsonProperty(value = "field") String field
            , @JsonProperty(value = "reason") String reason
            , @JsonProperty(value = "message") String message
            , @JsonProperty(value = "location") String location
            , @JsonProperty(value = "locationType") String locationType
            , @JsonProperty(value = "extendedHelp") String extendedHelp
            , @JsonProperty(value = "sendReport") String sendReport) {
        this.domain = domain;
        this.field = field;
        this.reason = reason;
        this.message = message;
        this.location = location;
        this.locationType = locationType;
        this.extendedHelp = extendedHelp;
        this.sendReport = sendReport;
    }

    public String getDomain() {
        return domain;
    }

    public String getField() {
        return field;
    }

    public String getReason() {
        return reason;
    }

    public String getMessage() {
        return message;
    }

    public String getLocation() {
        return location;
    }

    public String getLocationType() {
        return locationType;
    }

    public String getExtendedHelp() {
        return extendedHelp;
    }

    public String getSendReport() {
        return sendReport;
    }
}

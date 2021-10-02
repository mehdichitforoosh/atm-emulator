package com.energizeglobal.assignment.atm.controller.json.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 */
public class AuthenticatedJsonObject {

    @JsonProperty(value = "token", required = true)
    private final String token;

    public AuthenticatedJsonObject(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}

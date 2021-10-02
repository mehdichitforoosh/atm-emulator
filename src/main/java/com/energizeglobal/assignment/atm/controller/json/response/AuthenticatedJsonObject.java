package com.energizeglobal.assignment.atm.controller.json.response;

public class AuthenticatedJsonObject {

    private final String token;

    public AuthenticatedJsonObject(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}

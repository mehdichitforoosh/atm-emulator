package com.energizeglobal.assignment.atm.controller.json.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 */
public class AuthenticationJsonObject {

    private final String cardNumber;
    private final String pin;
    private final Long atmId;

    @JsonCreator
    public AuthenticationJsonObject(@JsonProperty(value = "cardNumber", required = true) String cardNumber
            , @JsonProperty(value = "pin", required = true) String pin
            , @JsonProperty(value = "atmId", required = true) Long atmId) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.atmId = atmId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public Long getAtmId() {
        return atmId;
    }
}

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

    @JsonCreator
    public AuthenticationJsonObject(@JsonProperty(value = "cardNumber", required = true) String cardNumber
            , @JsonProperty(value = "pin", required = true) String pin) {
        this.cardNumber = cardNumber;
        this.pin = pin;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPin() {
        return pin;
    }
}

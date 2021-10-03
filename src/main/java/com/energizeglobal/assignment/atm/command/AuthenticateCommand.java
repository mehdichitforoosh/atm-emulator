package com.energizeglobal.assignment.atm.command;

import com.energizeglobal.assignment.common.Command;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 */
public class AuthenticateCommand implements Command {

    private final String cardNumber;
    private final String pin;
    private final Long atmId;

    public AuthenticateCommand(String cardNumber, String pin, Long atmId) {
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

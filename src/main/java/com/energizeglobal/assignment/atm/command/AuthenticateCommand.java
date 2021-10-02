package com.energizeglobal.assignment.atm.command;

import com.energizeglobal.assignment.common.Command;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 */
public class AuthenticateCommand implements Command {

    private final String cardNumber;
    private final String pin;

    public AuthenticateCommand(String cardNumber, String pin) {
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

package com.energizeglobal.assignment.atm.command;


import com.energizeglobal.assignment.common.Command;
import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 */
public final class WithdrawCashCommand implements Command {

    private final String cardNumber;
    private final BigDecimal amount;
    private final DateTime date;

    public WithdrawCashCommand(String cardNumber, BigDecimal amount, DateTime date) {
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.date = date;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public DateTime getDate() {
        return date;
    }
}

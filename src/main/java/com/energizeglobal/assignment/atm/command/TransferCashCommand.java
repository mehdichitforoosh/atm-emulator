package com.energizeglobal.assignment.atm.command;


import com.energizeglobal.assignment.common.Command;
import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 */
public final class TransferCashCommand implements Command {

    private final BigDecimal amount;
    private final String toCardNumber;
    private final DateTime date;

    public TransferCashCommand(BigDecimal amount, String toCardNumber, DateTime date) {
        this.amount = amount;
        this.toCardNumber = toCardNumber;
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getToCardNumber() {
        return toCardNumber;
    }

    public DateTime getDate() {
        return date;
    }
}

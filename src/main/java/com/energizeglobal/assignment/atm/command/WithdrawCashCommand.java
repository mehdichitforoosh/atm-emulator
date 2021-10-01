package com.energizeglobal.assignment.atm.command;


import com.energizeglobal.assignment.common.Command;
import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 */
public final class WithdrawCashCommand implements Command {

    private final BigDecimal amount;
    private final DateTime date;

    public WithdrawCashCommand(BigDecimal amount, DateTime date) {
        this.amount = amount;
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public DateTime getDate() {
        return date;
    }
}

package com.energizeglobal.assignment.atm.command;


import com.energizeglobal.assignment.common.Command;
import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 */
public final class DepositCashCommand implements Command {

    private final Long accountId;
    private final BigDecimal amount;
    private final DateTime date;

    public DepositCashCommand(Long accountId, BigDecimal amount, DateTime date) {
        this.accountId = accountId;
        this.amount = amount;
        this.date = date;
    }

    public Long getAccountId() {
        return accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public DateTime getDate() {
        return date;
    }
}

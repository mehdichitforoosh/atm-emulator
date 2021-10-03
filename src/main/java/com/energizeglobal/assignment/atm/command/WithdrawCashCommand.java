package com.energizeglobal.assignment.atm.command;


import com.energizeglobal.assignment.common.Command;
import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 */
public final class WithdrawCashCommand implements Command {

    private final Long atmId;
    private final String cardNumber;
    private final BigDecimal amount;

    public WithdrawCashCommand(Long atmId, String cardNumber, BigDecimal amount) {
        this.atmId = atmId;
        this.cardNumber = cardNumber;
        this.amount = amount;
    }

    public Long getAtmId() {
        return atmId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

}

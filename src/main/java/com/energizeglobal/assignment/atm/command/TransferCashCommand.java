package com.energizeglobal.assignment.atm.command;


import com.energizeglobal.assignment.common.Command;
import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 */
public final class TransferCashCommand implements Command {

    private final Long atmId;
    private final BigDecimal amount;
    private final String fromCardNumber;
    private final String toCardNumber;

    public TransferCashCommand(Long atmId, BigDecimal amount, String fromCardNumber, String toCardNumber) {
        this.atmId = atmId;
        this.amount = amount;
        this.fromCardNumber = fromCardNumber;
        this.toCardNumber = toCardNumber;
    }

    public Long getAtmId() {
        return atmId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getFromCardNumber() {
        return fromCardNumber;
    }

    public String getToCardNumber() {
        return toCardNumber;
    }

}

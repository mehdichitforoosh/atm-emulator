package com.energizeglobal.assignment.atm.controller.json.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class CashTransferJsonObject {

    private final BigDecimal amount;
    private final String toCardNumber;

    @JsonCreator
    public CashTransferJsonObject(@JsonProperty(value = "amount", required = true) BigDecimal amount
            , @JsonProperty(value = "toCardNumber", required = true) String toCardNumber) {
        this.amount = amount;
        this.toCardNumber = toCardNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getToCardNumber() {
        return toCardNumber;
    }
}

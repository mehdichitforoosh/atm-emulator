package com.energizeglobal.assignment.atm.controller.json.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class CashWithdrawalJsonObject {

    private final BigDecimal amount;

    @JsonCreator
    public CashWithdrawalJsonObject(@JsonProperty(value = "amount", required = true) BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}

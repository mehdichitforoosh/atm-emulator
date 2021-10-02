package com.energizeglobal.assignment.atm.controller.json.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class CashDepositJsonObject {

    private final BigDecimal amount;

    @JsonCreator
    public CashDepositJsonObject(@JsonProperty(value = "amount", required = true) BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}

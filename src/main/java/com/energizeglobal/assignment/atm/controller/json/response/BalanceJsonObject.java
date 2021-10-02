package com.energizeglobal.assignment.atm.controller.json.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class BalanceJsonObject {

    @JsonProperty(value = "accountNumber", required = true)
    private final String accountNumber;

    @JsonProperty(value = "amount", required = true)
    private final BigDecimal amount;

    public BalanceJsonObject(String accountNumber, BigDecimal amount) {
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}

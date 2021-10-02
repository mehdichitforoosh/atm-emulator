package com.energizeglobal.assignment.atm.controller.json.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 */
public class AccountJsonObject {

    @JsonProperty(value = "accountNumber", required = true)
    private final String accountNumber;

    @JsonProperty(value = "accountUserName", required = true)
    private final String accountUserName;

    @JsonProperty(value = "amount", required = true)
    private final BigDecimal amount;

    @JsonProperty(value = "bankName", required = true)
    private final String bankName;

    @JsonProperty(value = "bankBranchNumber", required = true)
    private final String bankBranchNumber;

    public AccountJsonObject(String accountNumber, String accountUserName, BigDecimal amount, String bankName, String bankBranchNumber) {
        this.accountNumber = accountNumber;
        this.accountUserName = accountUserName;
        this.amount = amount;
        this.bankName = bankName;
        this.bankBranchNumber = bankBranchNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountUserName() {
        return accountUserName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBankBranchNumber() {
        return bankBranchNumber;
    }
}

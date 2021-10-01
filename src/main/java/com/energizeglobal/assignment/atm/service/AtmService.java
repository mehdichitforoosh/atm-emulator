package com.energizeglobal.assignment.atm.service;

import java.math.BigDecimal;

public interface AtmService {

    void withdraw(BigDecimal amount);

    void deposit(BigDecimal amount);

    BigDecimal getBalance();

}

package com.energizeglobal.assignment.atm.service;

import com.energizeglobal.assignment.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AtmServiceImpl implements AtmService {

    private final AccountService accountService;

    @Autowired
    public AtmServiceImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void withdraw(BigDecimal amount) {

    }

    @Override
    public void deposit(BigDecimal amount) {

    }

    @Override
    public BigDecimal getBalance() {
        return null;
    }
}

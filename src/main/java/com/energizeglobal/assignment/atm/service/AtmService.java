package com.energizeglobal.assignment.atm.service;

import com.energizeglobal.assignment.atm.command.AuthenticateCommand;
import com.energizeglobal.assignment.atm.command.DepositCashCommand;
import com.energizeglobal.assignment.atm.command.TransferCashCommand;
import com.energizeglobal.assignment.atm.command.WithdrawCashCommand;
import com.energizeglobal.assignment.atm.domain.Atm;

import java.math.BigDecimal;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 */
public interface AtmService {

    Atm getByAtmId(Long atmId);

    String getToken(AuthenticateCommand command);

    void withdraw(WithdrawCashCommand command);

    void deposit(DepositCashCommand command);

    void transfer(TransferCashCommand command);

    boolean isAvailableCash(BigDecimal amount, Long atmId);

}

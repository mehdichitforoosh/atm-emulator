package com.energizeglobal.assignment.atm.service;

import com.energizeglobal.assignment.atm.command.AuthenticateCommand;
import com.energizeglobal.assignment.atm.command.DepositCashCommand;
import com.energizeglobal.assignment.atm.command.TransferCashCommand;
import com.energizeglobal.assignment.atm.command.WithdrawCashCommand;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 */
public interface AtmService {

    String getToken(AuthenticateCommand command);

    void withdraw(WithdrawCashCommand command);

    void deposit(DepositCashCommand command);

    void transfer(TransferCashCommand command);

}

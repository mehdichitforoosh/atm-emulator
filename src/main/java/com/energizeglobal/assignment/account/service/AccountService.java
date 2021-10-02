package com.energizeglobal.assignment.account.service;

import com.energizeglobal.assignment.account.domain.Account;

import java.math.BigDecimal;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 */
public interface AccountService {

    Account getByAccountId(Long accountId);

    Account getByAccountNumber(String accountNumber);

    BigDecimal getBalance(Long accountId);

    BigDecimal getBalance(String accountNumber);

}

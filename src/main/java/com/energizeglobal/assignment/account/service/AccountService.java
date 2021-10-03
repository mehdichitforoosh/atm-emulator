package com.energizeglobal.assignment.account.service;

import java.math.BigDecimal;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 */
public interface AccountService {

    void addAmountById(BigDecimal amount, Long accountId);

    void subtractAmountById(BigDecimal amount, Long accountId);

}

package com.energizeglobal.assignment.bank.service;

import com.energizeglobal.assignment.bank.domain.Bank;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 */
public interface BankService {

    Bank getById(Long id);

    Bank getByBranchNumber(String branchNumber);

}

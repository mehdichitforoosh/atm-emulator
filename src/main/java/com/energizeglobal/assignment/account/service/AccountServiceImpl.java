package com.energizeglobal.assignment.account.service;

import com.energizeglobal.assignment.account.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 */
@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Add amount to account balance
     *
     * @param amount
     * @param accountId
     */
    @Override
    @Transactional
    public void addAmountById(BigDecimal amount, Long accountId) {
        logger.info("add amount to account by " + accountId);
        accountRepository.addAmountById(amount, accountId);
    }

    /**
     * Subtract amount from account balance
     *
     * @param amount
     * @param accountId
     */
    @Override
    @Transactional
    public void subtractAmountById(BigDecimal amount, Long accountId) {
        logger.info("subtract amount to account by " + accountId);
        accountRepository.subtractAmountById(amount, accountId);
    }
}

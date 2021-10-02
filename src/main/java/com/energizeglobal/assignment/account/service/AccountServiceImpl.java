package com.energizeglobal.assignment.account.service;

import com.energizeglobal.assignment.account.domain.Account;
import com.energizeglobal.assignment.account.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

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
     * Find account by account id
     *
     * @param accountId
     * @return account
     * @throws EmptyResultDataAccessException if account doesn't exist
     */
    @Override
    @Transactional(readOnly = true)
    public Account getByAccountId(Long accountId) {
        logger.info("find one account by " + accountId);
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if (optionalAccount.isPresent()) {
            return optionalAccount.get();
        }
        throw new EmptyResultDataAccessException("account is empty.", 1);
    }

    /**
     * Find account by account number
     *
     * @param accountNumber
     * @return account
     * @throws EmptyResultDataAccessException if account doesn't exist
     */
    @Override
    @Transactional(readOnly = true)
    public Account getByAccountNumber(String accountNumber) {
        logger.info("find one account by " + accountNumber);
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account != null) {
            return account;
        }
        throw new EmptyResultDataAccessException("account is empty.", 1);
    }

    /**
     * Get account balance by account id
     *
     * @param accountId
     * @return balance
     * @throws EmptyResultDataAccessException if account doesn't exist
     */
    @Override
    @Transactional(readOnly = true)
    public BigDecimal getBalance(Long accountId) {
        logger.info("find account balance by " + accountId);
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            return account.getBalance();
        }
        throw new EmptyResultDataAccessException("account is empty.", 1);
    }

    /**
     * Find account balance by account number
     *
     * @param accountNumber
     * @return account
     * @throws EmptyResultDataAccessException if account doesn't exist
     */
    @Override
    @Transactional(readOnly = true)
    public BigDecimal getBalance(String accountNumber) {
        logger.info("find account balance by " + accountNumber);
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account != null) {
            return account.getBalance();
        }
        throw new EmptyResultDataAccessException("account is empty.", 1);
    }
}

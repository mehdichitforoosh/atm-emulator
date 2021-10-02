package com.energizeglobal.assignment.bank.service;

import com.energizeglobal.assignment.bank.domain.Bank;
import com.energizeglobal.assignment.bank.repository.BankRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 */
@Service
public class BankServiceImpl implements BankService {

    private static final Logger logger = LoggerFactory.getLogger(BankServiceImpl.class);

    private final BankRepository bankRepository;

    @Autowired
    public BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    /**
     * Find bank by id
     *
     * @param id bank id
     * @return bank
     * @throws EmptyResultDataAccessException if bank doesn't exist
     */
    @Override
    @Transactional(readOnly = true)
    public Bank getById(Long id) {
        logger.info("find one bank by " + id);
        Bank bank = bankRepository.findById(id);
        if (bank != null) {
            return bank;
        }
        throw new EmptyResultDataAccessException("bank is empty.", 1);
    }

    /**
     * Find bank by branch number
     *
     * @param branchNumber bank branch number
     * @return bank
     * @throws EmptyResultDataAccessException if bank doesn't exist
     */
    @Override
    @Transactional(readOnly = true)
    public Bank getByBranchNumber(String branchNumber) {
        logger.info("find one bank by " + branchNumber);
        Bank bank = bankRepository.findByBranchNumber(branchNumber);
        if (bank != null) {
            return bank;
        }
        throw new EmptyResultDataAccessException("bank is empty.", 1);
    }
}

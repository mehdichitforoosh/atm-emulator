package com.energizeglobal.assignment.bank.repository;

import com.energizeglobal.assignment.bank.domain.Bank;
import org.springframework.data.repository.Repository;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 * Spring Data JPA Repository for {@link Bank} entity.
 */
public interface BankRepository extends Repository<Bank, Long> {

    Bank findById(Long id);

}

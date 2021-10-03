package com.energizeglobal.assignment.account.repository;

import com.energizeglobal.assignment.account.domain.Account;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 * Spring Data JPA Repository for {@link Account} entity.
 */
public interface AccountRepository extends CrudRepository<Account, Long> {

    Account findByAccountNumber(String accountNumber);

    @Modifying
    @Query("update Account a set a.balance = a.balance + ?1 where a.id = ?2")
    void addAmountById(BigDecimal amount, Long accountId);

    @Modifying
    @Query("update Account a set a.balance = a.balance - ?1 where a.id = ?2")
    void subtractAmountById(BigDecimal amount, Long accountId);

}

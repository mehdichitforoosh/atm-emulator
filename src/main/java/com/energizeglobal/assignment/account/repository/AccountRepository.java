package com.energizeglobal.assignment.account.repository;

import com.energizeglobal.assignment.account.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 * Spring Data JPA Repository for {@link Account} entity.
 */
public interface AccountRepository extends CrudRepository<Account, Long> {

    Account findByAccountNumber(String accountNumber);

}

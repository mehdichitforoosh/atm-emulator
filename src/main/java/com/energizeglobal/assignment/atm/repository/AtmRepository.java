package com.energizeglobal.assignment.atm.repository;

import com.energizeglobal.assignment.atm.domain.Atm;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 * Spring Data JPA Repository for {@link Atm} entity.
 */
public interface AtmRepository extends CrudRepository<Atm, Long> {

    @Modifying
    @Query("update Atm a set a.availableCash = a.availableCash + ?1 where a.id = ?2")
    void addAmountById(BigDecimal amount, Long atmId);

    @Modifying
    @Query("update Atm a set a.availableCash = a.availableCash - ?1 where a.id = ?2")
    void subtractAmountById(BigDecimal amount, Long atmId);

}

package com.energizeglobal.assignment.atm.repository;

import com.energizeglobal.assignment.atm.domain.Atm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 * Spring Data JPA Repository for {@link Atm} entity.
 */
public interface AtmRepository extends CrudRepository<Atm, Long> {

    Atm findByAtmNumber(String atmNumber);

}

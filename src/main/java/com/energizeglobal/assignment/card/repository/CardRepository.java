package com.energizeglobal.assignment.card.repository;

import com.energizeglobal.assignment.card.domain.Card;
import org.springframework.data.repository.Repository;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 * Spring Data JPA Repository for {@link Card} entity.
 */
public interface CardRepository extends Repository<Card, Long> {

    Card findByCardNumber(String accountNumber);

    Card findByAccountId(Long accountId);

}

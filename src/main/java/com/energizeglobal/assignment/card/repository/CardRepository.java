package com.energizeglobal.assignment.card.repository;

import com.energizeglobal.assignment.card.domain.Card;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 * Spring Data JPA Repository for {@link Card} entity.
 */
public interface CardRepository extends Repository<Card, Long> {

    Card findByCardNumber(String accountNumber);

    Card findByAccountId(Long accountId);

    @Modifying
    @Query("update Card c set c.tryWithError = c.tryWithError + 1 where c.cardNumber = ?1")
    void addTryWithErrorByCardNumber(String cardNumber);

    @Modifying
    @Query("update Card c set c.active = ?1 where c.cardNumber = ?2")
    void setActiveByCardNumber(Boolean active, String cardNumber);

}

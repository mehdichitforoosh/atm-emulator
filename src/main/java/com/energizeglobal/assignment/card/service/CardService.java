package com.energizeglobal.assignment.card.service;

import com.energizeglobal.assignment.card.domain.Card;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 */
public interface CardService {

    Card getByCardNumber(String cardNumber);

    Card getByAccountId(Long accountId);

}

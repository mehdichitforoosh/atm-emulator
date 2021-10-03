package com.energizeglobal.assignment.card.service;

import com.energizeglobal.assignment.card.domain.Card;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 */
public interface CardService {

    Card getByCardNumber(String cardNumber);

    void addTryWithErrorByCardNumber(String cardNumber);

    void setActiveByCardNumber(Boolean active, String cardNumber);

}

package com.energizeglobal.assignment.card.service;

import com.energizeglobal.assignment.card.domain.Card;
import com.energizeglobal.assignment.card.repository.CardRepository;
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
public class CardServiceImpl implements CardService {

    private static final Logger logger = LoggerFactory.getLogger(CardServiceImpl.class);

    private final CardRepository cardRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    /**
     * Find card by card number
     *
     * @param cardNumber card number
     * @return card
     * @throws EmptyResultDataAccessException if card doesn't exist
     */
    @Override
    @Transactional(readOnly = true)
    public Card getByCardNumber(String cardNumber) {
        logger.info("find one card by " + cardNumber);
        Card card = cardRepository.findByCardNumber(cardNumber);
        if (card != null) {
            return card;
        }
        throw new EmptyResultDataAccessException("card is empty.", 1);
    }

    /**
     * Add try with error to card
     *
     * @param cardNumber
     */
    @Override
    @Transactional
    public void addTryWithErrorByCardNumber(String cardNumber) {
        logger.info("add try with error by " + cardNumber);
        cardRepository.addTryWithErrorByCardNumber(cardNumber);
    }

    /**
     * Set active status on card
     *
     * @param active
     * @param cardNumber
     */
    @Override
    @Transactional
    public void setActiveByCardNumber(Boolean active, String cardNumber) {
        logger.info("set active " + active + " by " + cardNumber);
        cardRepository.setActiveByCardNumber(active, cardNumber);
    }
}

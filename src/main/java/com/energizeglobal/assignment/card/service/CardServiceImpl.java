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
     * Find card by id
     *
     * @param id card id
     * @return card
     * @throws EmptyResultDataAccessException if card doesn't exist
     */
    @Override
    @Transactional(readOnly = true)
    public Card getById(Long id) {
        logger.info("find one card by " + id);
        Card card = cardRepository.findById(id);
        if (card != null) {
            return card;
        }
        throw new EmptyResultDataAccessException("card is empty.", 1);
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
     * Find card by account id
     *
     * @param accountId account id
     * @return card
     * @throws EmptyResultDataAccessException if card doesn't exist
     */
    @Override
    @Transactional(readOnly = true)
    public Card getByAccountId(Long accountId) {
        logger.info("find one card by " + accountId);
        Card card = cardRepository.findByAccountId(accountId);
        if (card != null) {
            return card;
        }
        throw new EmptyResultDataAccessException("card is empty.", 1);
    }
}

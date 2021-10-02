package com.energizeglobal.assignment.security;

import com.energizeglobal.assignment.card.domain.Card;
import com.energizeglobal.assignment.card.service.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class JPAUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(JPAUserDetailsService.class);

    private final CardService cardService;

    @Autowired
    public JPAUserDetailsService(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public UserDetails loadUserByUsername(String cardNumber) throws UsernameNotFoundException {
        Card card = cardService.getByCardNumber(cardNumber);
        if (card == null) {
            logger.debug("Query returned no results for card '" + cardNumber + "'");
            throw new UsernameNotFoundException("Card number " + cardNumber + " not found");
        } else {
            String password = card.getPin();
            return new User(cardNumber, password, Collections.emptyList());
        }
    }
}

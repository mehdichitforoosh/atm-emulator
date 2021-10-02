package com.energizeglobal.assignment.atm.service;

import com.energizeglobal.assignment.account.service.AccountService;
import com.energizeglobal.assignment.atm.command.AuthenticateCommand;
import com.energizeglobal.assignment.atm.command.DepositCashCommand;
import com.energizeglobal.assignment.atm.command.TransferCashCommand;
import com.energizeglobal.assignment.atm.command.WithdrawCashCommand;
import com.energizeglobal.assignment.card.service.CardService;
import com.energizeglobal.assignment.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 */
@Service
public class AtmServiceImpl implements AtmService {

    private static final Logger logger = LoggerFactory.getLogger(AtmServiceImpl.class);

    private final AccountService accountService;
    private final CardService cardService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AtmServiceImpl(AccountService accountService
            , CardService cardService
            , AuthenticationManager authenticationManager
            , UserDetailsService userDetailsService
            , JwtTokenUtil jwtTokenUtil) {
        this.accountService = accountService;
        this.cardService = cardService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    @Transactional(readOnly = true)
    public String getToken(AuthenticateCommand command) {
        logger.info("authenticate card number ...");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(command.getCardNumber(), command.getPin()));
            final UserDetails userDetails = userDetailsService.loadUserByUsername(command.getCardNumber());
            final String jwt = jwtTokenUtil.generateToken(userDetails);
            return jwt;
        } catch (AuthenticationException ex) {
            //todo add block card for 3 times
            throw ex;
        }
    }

    @Override
    @Transactional
    public void withdraw(WithdrawCashCommand command) {
        // check cash dispense or atm amount and decrease
    }

    @Override
    @Transactional
    public void deposit(DepositCashCommand command) {

    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void transfer(TransferCashCommand command) {

    }

}

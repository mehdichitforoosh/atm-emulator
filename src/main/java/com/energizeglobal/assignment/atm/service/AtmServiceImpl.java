package com.energizeglobal.assignment.atm.service;

import com.energizeglobal.assignment.account.domain.Account;
import com.energizeglobal.assignment.account.service.AccountService;
import com.energizeglobal.assignment.atm.command.AuthenticateCommand;
import com.energizeglobal.assignment.atm.command.DepositCashCommand;
import com.energizeglobal.assignment.atm.command.TransferCashCommand;
import com.energizeglobal.assignment.atm.command.WithdrawCashCommand;
import com.energizeglobal.assignment.atm.domain.Atm;
import com.energizeglobal.assignment.atm.repository.AtmRepository;
import com.energizeglobal.assignment.card.domain.Card;
import com.energizeglobal.assignment.card.service.CardService;
import com.energizeglobal.assignment.exception.CardBlockedException;
import com.energizeglobal.assignment.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 */
@Service
public class AtmServiceImpl implements AtmService {

    private static final Logger logger = LoggerFactory.getLogger(AtmServiceImpl.class);

    private final AtmRepository atmRepository;
    private final AccountService accountService;
    private final CardService cardService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AtmServiceImpl(AtmRepository atmRepository
            , AccountService accountService
            , CardService cardService
            , AuthenticationManager authenticationManager
            , UserDetailsService userDetailsService
            , JwtTokenUtil jwtTokenUtil) {
        this.atmRepository = atmRepository;
        this.accountService = accountService;
        this.cardService = cardService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    /**
     * Find atm by atm id
     *
     * @param atmId
     * @return atm
     * @throws EmptyResultDataAccessException if atm doesn't exist
     */
    @Override
    @Transactional(readOnly = true)
    public Atm getByAtmId(Long atmId) {
        logger.info("find one atm by " + atmId);
        Optional<Atm> optionalAtm = atmRepository.findById(atmId);
        if (optionalAtm.isPresent()) {
            return optionalAtm.get();
        }
        throw new EmptyResultDataAccessException("atm is empty.", 1);
    }

    /**
     * Authenticate card and get Jwt token
     *
     * @param command
     * @return token
     * @throws AuthenticationException
     * @throws CardBlockedException
     */
    @Override
    @Transactional(noRollbackFor = AuthenticationException.class)
    public String getToken(AuthenticateCommand command) {
        logger.info("authenticate card number ...");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(command.getCardNumber(), command.getPin()));
            final UserDetails userDetails = userDetailsService.loadUserByUsername(command.getCardNumber());
            Card card = cardService.getByCardNumber(command.getCardNumber());
            // Throw card blocked exception if card is not active
            if (!card.getActive()) {
                throw new CardBlockedException("card is blocked");
            }
            Map<String, Object> claims = new HashMap<>();
            claims.put("atmId", command.getAtmId());
            final String jwt = jwtTokenUtil.generateToken(userDetails, claims);
            return jwt;
        } catch (AuthenticationException ex) {
            // Block card after three unsuccessful attempts
            Card card = cardService.getByCardNumber(command.getCardNumber());
            Integer tryWithError = card.getTryWithError();
            if (tryWithError < 2) {
                // Add try counts on authentication failure
                cardService.addTryWithErrorByCardNumber(command.getCardNumber());
            } else {
                cardService.setActiveByCardNumber(false, command.getCardNumber());
            }
            throw ex;
        }
    }

    /**
     * Withdraw amount from account balance and subtract from atm available cash
     *
     * @param command
     */
    @Override
    @Transactional
    public void withdraw(WithdrawCashCommand command) {
        Card card = cardService.getByCardNumber(command.getCardNumber());
        Account account = card.getAccount();
        // Subtract cash from atm and subtract amount from account balance
        accountService.subtractAmountById(command.getAmount(), account.getId());
        atmRepository.subtractAmountById(command.getAmount(), command.getAtmId());
    }

    /**
     * Deposit amount to account balance and add to atm available cash
     *
     * @param command
     */
    @Override
    @Transactional
    public void deposit(DepositCashCommand command) {
        Card card = cardService.getByCardNumber(command.getCardNumber());
        Account account = card.getAccount();
        // Add cash to atm and add amount to account balance
        accountService.addAmountById(command.getAmount(), account.getId());
        atmRepository.addAmountById(command.getAmount(), command.getAtmId());
    }

    @Override
    @Transactional
    public void transfer(TransferCashCommand command) {
        Card fromCard = cardService.getByCardNumber(command.getFromCardNumber());
        Account fromAccount = fromCard.getAccount();
        accountService.subtractAmountById(command.getAmount(), fromAccount.getId());
        Card toCard = cardService.getByCardNumber(command.getToCardNumber());
        Account toAccount = toCard.getAccount();
        accountService.addAmountById(command.getAmount(), toAccount.getId());
    }


    /**
     * Check available cash n ATM cash dispenser
     *
     * @param amount
     * @param atmId
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public boolean isAvailableCash(BigDecimal amount, Long atmId) {
        Optional<Atm> optionalAtm = atmRepository.findById(atmId);
        if (optionalAtm.isPresent()) {
            Atm atm = optionalAtm.get();
            BigDecimal availableCash = atm.getAvailableCash();
            if (amount.compareTo(availableCash) > 0) {
                return false;
            } else {
                return true;
            }
        }
        throw new EmptyResultDataAccessException("atm is empty.", 1);
    }

}

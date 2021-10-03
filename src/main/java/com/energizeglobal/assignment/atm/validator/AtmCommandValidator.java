package com.energizeglobal.assignment.atm.validator;


import com.energizeglobal.assignment.account.domain.Account;
import com.energizeglobal.assignment.atm.command.AuthenticateCommand;
import com.energizeglobal.assignment.atm.command.DepositCashCommand;
import com.energizeglobal.assignment.atm.command.TransferCashCommand;
import com.energizeglobal.assignment.atm.command.WithdrawCashCommand;
import com.energizeglobal.assignment.atm.service.AtmService;
import com.energizeglobal.assignment.card.domain.Card;
import com.energizeglobal.assignment.card.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 * Atm Command validator
 */
@Component
public class AtmCommandValidator implements Validator {

    private final AtmService atmService;
    private final CardService cardService;

    @Autowired
    public AtmCommandValidator(AtmService atmService, CardService cardService) {
        this.atmService = atmService;
        this.cardService = cardService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return AuthenticateCommand.class.equals(aClass)
                || WithdrawCashCommand.class.equals(aClass)
                || DepositCashCommand.class.equals(aClass)
                || TransferCashCommand.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (o instanceof AuthenticateCommand) {
            AuthenticateCommand command = (AuthenticateCommand) o;
            Long atmId = command.getAtmId();
            String cardNumber = command.getCardNumber();
            String pin = command.getPin();
            checkCardNumber(cardNumber, errors);
            checkPin(pin, errors);
            checkAtm(atmId, errors);
        } else if (o instanceof WithdrawCashCommand) {
            WithdrawCashCommand command = (WithdrawCashCommand) o;
            Long atmId = command.getAtmId();
            String cardNumber = command.getCardNumber();
            BigDecimal amount = command.getAmount();
            checkCardNumber(cardNumber, errors);
            checkAmount(amount, errors);
            checkAccountBalanceAvailability(cardNumber, amount, errors);
            checkCashAvailability(atmId, amount, errors);
        } else if (o instanceof DepositCashCommand) {
            DepositCashCommand command = (DepositCashCommand) o;
            Long atmId = command.getAtmId();
            String cardNumber = command.getCardNumber();
            BigDecimal amount = command.getAmount();
            checkCardNumber(cardNumber, errors);
            checkAmount(amount, errors);
        } else if (o instanceof TransferCashCommand) {
            TransferCashCommand command = (TransferCashCommand) o;
            BigDecimal amount = command.getAmount();
            String fromCardNumber = command.getFromCardNumber();
            String toCardNumber = command.getToCardNumber();
            checkCardNumber(fromCardNumber, errors);
            checkCardNumber(toCardNumber, errors);
            checkAmount(amount, errors);
            checkAccountBalanceAvailability(fromCardNumber, amount, errors);
        }
    }

    private void checkAmount(BigDecimal amount, Errors errors) {
        if (!(amount.compareTo(BigDecimal.valueOf(0)) > 0)) {
            errors.rejectValue("amount", "invalid");
        }
    }

    private void checkCardNumber(String cardNumber, Errors errors) {
        Pattern pattern = Pattern.compile("\\d{16}");
        Matcher matcher = pattern.matcher(cardNumber);
        if (!matcher.matches()) {
            errors.rejectValue("cardNumber", "invalid");
        }
    }

    private void checkPin(String pin, Errors errors) {
        Pattern pattern = Pattern.compile("\\d{4}");
        Matcher matcher = pattern.matcher(pin);
        if (!matcher.matches()) {
            errors.rejectValue("pin", "invalid");
        }
    }

    private void checkAtm(Long atmId, Errors errors) {
        try {
            atmService.getByAtmId(atmId);
        } catch (EmptyResultDataAccessException ex) {
            errors.rejectValue("atmId", "invalid");
        }
    }

    private void checkAccountBalanceAvailability(String cardNumber, BigDecimal amount, Errors errors) {
        try {
            Card card = cardService.getByCardNumber(cardNumber);
            Account account = card.getAccount();
            BigDecimal balance = account.getBalance();
            if (!(balance.compareTo(amount) >= 0)) {
                errors.reject("notAvailableBalance");
            }
        } catch (EmptyResultDataAccessException ex) {
            errors.rejectValue("cardNumber", "notFound");
        }
    }

    private void checkCashAvailability(Long atmId, BigDecimal amount, Errors errors) {
        try {
            boolean isAvailable = atmService.isAvailableCash(amount, atmId);
            if (!isAvailable) {
                errors.rejectValue("amount", "notAvailable");
            }
        } catch (EmptyResultDataAccessException ex) {
            errors.rejectValue("atmId", "invalid");
        }
    }

}

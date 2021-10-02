package com.energizeglobal.assignment.atm.validator;


import com.energizeglobal.assignment.atm.command.AuthenticateCommand;
import com.energizeglobal.assignment.atm.command.DepositCashCommand;
import com.energizeglobal.assignment.atm.command.TransferCashCommand;
import com.energizeglobal.assignment.atm.command.WithdrawCashCommand;
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
            String cardNumber = command.getCardNumber();
            String pin = command.getPin();
            checkCardNumber(cardNumber, errors);
            checkPin(pin, errors);
        } else if (o instanceof WithdrawCashCommand) {
            WithdrawCashCommand command = (WithdrawCashCommand) o;
            String cardNumber = command.getCardNumber();
            BigDecimal amount = command.getAmount();
            checkCardNumber(cardNumber, errors);
            checkAmount(amount, errors);
        } else if (o instanceof DepositCashCommand) {
            DepositCashCommand command = (DepositCashCommand) o;
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

}

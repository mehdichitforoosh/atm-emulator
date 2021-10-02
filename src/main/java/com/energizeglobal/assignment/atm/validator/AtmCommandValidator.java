package com.energizeglobal.assignment.atm.validator;


import com.energizeglobal.assignment.atm.command.AuthenticateCommand;
import com.energizeglobal.assignment.atm.command.DepositCashCommand;
import com.energizeglobal.assignment.atm.command.TransferCashCommand;
import com.energizeglobal.assignment.atm.command.WithdrawCashCommand;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;

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
//            checkCommonProperties(name, personnelId, tags, description, errors);
            if (errors.hasErrors()) {
                return;
            }
        } else if (o instanceof WithdrawCashCommand) {
            WithdrawCashCommand command = (WithdrawCashCommand) o;
            BigDecimal amount = command.getAmount();
            DateTime date = command.getDate();
//            checkCommonProperties(name, personnelId, tags, description, errors);
            if (errors.hasErrors()) {
                return;
            }
        } else if (o instanceof DepositCashCommand) {
            DepositCashCommand command = (DepositCashCommand) o;
            BigDecimal amount = command.getAmount();
            DateTime date = command.getDate();
            // Low processing cost validation checks comes first
//            checkCommonProperties(name, personnelId, tags, description, errors);
            if (errors.hasErrors()) {
                return;
            }
        } else if (o instanceof TransferCashCommand) {
            TransferCashCommand command = (TransferCashCommand) o;
            BigDecimal amount = command.getAmount();
            String toCardNumber = command.getToCardNumber();
            DateTime date = command.getDate();
        }
    }

//    private void checkCommonProperties(String name, String personnelId, String[] tags, String description, Errors errors) {
//        if (StringUtils.isNotEmpty(name)) {
//            if (!(name.length() >= 2 && name.length() <= 30)) {
//                errors.rejectValue("name", "invalid");
//            }
//        } else {
//            errors.rejectValue("name", "invalid");
//        }
//        if (StringUtils.isNotEmpty(description)) {
//            if (!(description.length() <= 2000)) {
//                errors.rejectValue("description", "invalid");
//            }
//        }
//    }

}

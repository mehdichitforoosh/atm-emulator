package com.energizeglobal.assignment.atm.controller;

import com.energizeglobal.assignment.atm.command.DepositCashCommand;
import com.energizeglobal.assignment.atm.command.TransferCashCommand;
import com.energizeglobal.assignment.atm.command.WithdrawCashCommand;
import com.energizeglobal.assignment.atm.controller.json.CashDepositJsonObject;
import com.energizeglobal.assignment.atm.controller.json.CashTransferJsonObject;
import com.energizeglobal.assignment.atm.controller.json.CashWithdrawalJsonObject;
import com.energizeglobal.assignment.atm.service.AtmService;
import com.energizeglobal.assignment.atm.validator.AtmCommandValidator;
import com.energizeglobal.assignment.common.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.concurrent.Callable;

@RestController
@RequestMapping("/atm")
public class AtmController {

    private static final Logger logger = LoggerFactory.getLogger(AtmController.class);

    private final AtmService atmService;
    private final AtmCommandValidator atmCommandValidator;

    @Autowired
    public AtmController(AtmService atmService, AtmCommandValidator atmCommandValidator) {
        this.atmService = atmService;
        this.atmCommandValidator = atmCommandValidator;
    }

    @GetMapping(value = "/balance", produces = MediaType.APPLICATION_JSON_VALUE)
    public Callable<ResponseEntity<?>> getBalance(final Principal principal) {
        logger.info("Get balance ...");
        return () -> {
            // Add validation and service calls
            return new ResponseEntity<>(HttpStatus.OK);
        };
    }

    @PutMapping(value = "/deposit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Callable<ResponseEntity<?>> doDeposit(@RequestBody final CashDepositJsonObject jsonObject, final Principal principal) {
        logger.info("Deposit cash ...");
        return () -> {
            // Add validation and service calls
            DepositCashCommand command = new DepositCashCommand(null, null);
            validate(command);
            return new ResponseEntity<>(HttpStatus.OK);
        };
    }

    @PutMapping(value = "/withdrawal", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Callable<ResponseEntity<?>> doWithdrawal(@RequestBody final CashWithdrawalJsonObject jsonObject, final Principal principal) {
        logger.info("Withdrawal cash ...");
        return () -> {
            // Add validation and service calls
            WithdrawCashCommand command = new WithdrawCashCommand(null, null);
            validate(command);
            return new ResponseEntity<>(HttpStatus.OK);
        };
    }

    @PutMapping(value = "/transfer", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Callable<ResponseEntity<?>> doTransfer(@RequestBody final CashTransferJsonObject jsonObject, final Principal principal) {
        logger.info("Transfer cash ...");
        return () -> {
            // Add validation and service calls
            TransferCashCommand command = new TransferCashCommand(null, "", null);
            validate(command);
            return new ResponseEntity<>(HttpStatus.OK);
        };
    }

    private void validate(Command command) {
        Assert.notNull(command, "Command should not be null.");
        BeanPropertyBindingResult results = new BeanPropertyBindingResult(command, "atm");
        atmCommandValidator.validate(command, results);
        if (results.hasErrors()) {
//            throw new ValidationException(results);
        }
    }
}

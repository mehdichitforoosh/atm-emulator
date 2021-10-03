package com.energizeglobal.assignment.atm.controller;

import com.energizeglobal.assignment.account.domain.Account;
import com.energizeglobal.assignment.account.service.AccountService;
import com.energizeglobal.assignment.atm.command.AuthenticateCommand;
import com.energizeglobal.assignment.atm.command.DepositCashCommand;
import com.energizeglobal.assignment.atm.command.TransferCashCommand;
import com.energizeglobal.assignment.atm.command.WithdrawCashCommand;
import com.energizeglobal.assignment.atm.controller.json.request.AuthenticationJsonObject;
import com.energizeglobal.assignment.atm.controller.json.request.CashDepositJsonObject;
import com.energizeglobal.assignment.atm.controller.json.request.CashTransferJsonObject;
import com.energizeglobal.assignment.atm.controller.json.request.CashWithdrawalJsonObject;
import com.energizeglobal.assignment.atm.controller.json.response.AccountJsonObject;
import com.energizeglobal.assignment.atm.controller.json.response.AuthenticatedJsonObject;
import com.energizeglobal.assignment.atm.controller.json.response.BalanceJsonObject;
import com.energizeglobal.assignment.atm.service.AtmService;
import com.energizeglobal.assignment.atm.validator.AtmCommandValidator;
import com.energizeglobal.assignment.bank.domain.Bank;
import com.energizeglobal.assignment.card.domain.Card;
import com.energizeglobal.assignment.card.service.CardService;
import com.energizeglobal.assignment.common.Command;
import com.energizeglobal.assignment.exception.ValidationException;
import com.energizeglobal.assignment.security.AtmUser;
import com.energizeglobal.assignment.user.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.util.Assert;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.concurrent.Callable;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/atm")
@Api(value = "ATM Controller for interacting", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class AtmController {

    private static final Logger logger = LoggerFactory.getLogger(AtmController.class);

    private final AtmService atmService;
    private final AccountService accountService;
    private final CardService cardService;
    private final AtmCommandValidator atmCommandValidator;

    @Autowired
    public AtmController(AtmService atmService
            , AccountService accountService
            , CardService cardService
            , AtmCommandValidator atmCommandValidator) {
        this.atmService = atmService;
        this.accountService = accountService;
        this.cardService = cardService;
        this.atmCommandValidator = atmCommandValidator;
    }

    @PostMapping(value = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Authenticate with card number and pin", response = AuthenticatedJsonObject.class)
    public Callable<ResponseEntity<?>> authenticate(@RequestBody final AuthenticationJsonObject jsonObject) {
        logger.info("authenticate card ...");
        return () -> {
            AuthenticateCommand command = new AuthenticateCommand(jsonObject.getCardNumber(), jsonObject.getPin(), jsonObject.getAtmId());
            validate(command);
            String jwtToken = atmService.getToken(command);
            return ResponseEntity.ok(new AuthenticatedJsonObject(jwtToken));
        };
    }

    @GetMapping(value = "/account/info", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get account information", response = AccountJsonObject.class)
    public Callable<ResponseEntity<?>> getAccountInfo(final Principal principal) {
        logger.info("Get account info ...");
        return () -> {
            // Get card number from security context
            UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
            AtmUser atmUser = (AtmUser) authenticationToken.getPrincipal();
            String cardNumber = atmUser.getUsername();
            // Retrieve card entity
            Card card = cardService.getByCardNumber(cardNumber);
            Account account = card.getAccount();
            Bank bank = account.getBank();
            User user = account.getUser();
            String userFullName = user.getFirstName() + " " + user.getLastName();
            AccountJsonObject jsonObject = new AccountJsonObject(account.getAccountNumber()
                    , userFullName, account.getBalance()
                    , bank.getName()
                    , bank.getBranchNumber());
            return ResponseEntity.ok(jsonObject);
        };
    }

    @GetMapping(value = "/account/balance", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get account balance", response = BalanceJsonObject.class)
    public Callable<ResponseEntity<?>> getBalance(final Principal principal) {
        logger.info("Get balance ...");
        return () -> {
            // Get card number from security context
            UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
            AtmUser atmUser = (AtmUser) authenticationToken.getPrincipal();
            String cardNumber = atmUser.getUsername();
            // Retrieve card entity
            Card card = cardService.getByCardNumber(cardNumber);
            Account account = card.getAccount();
            return ResponseEntity.ok(new BalanceJsonObject(account.getAccountNumber(), account.getBalance()));
        };
    }

    @PutMapping(value = "/deposit", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Deposit cash")
    public Callable<ResponseEntity<?>> doDeposit(@RequestBody final CashDepositJsonObject jsonObject, final Principal principal) {
        logger.info("Deposit cash ...");
        return () -> {
            // Get card number from security context
            UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
            AtmUser atmUser = (AtmUser) authenticationToken.getPrincipal();
            String cardNumber = atmUser.getUsername();
            Long atmId = atmUser.getAtmId();
            // Add validation and service calls
            DepositCashCommand command = new DepositCashCommand(atmId, cardNumber, jsonObject.getAmount());
            validate(command);
            atmService.deposit(command);
            return ResponseEntity.ok(null);
        };
    }

    @PutMapping(value = "/withdrawal", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Withdraw cash")
    public Callable<ResponseEntity<?>> doWithdrawal(@RequestBody final CashWithdrawalJsonObject jsonObject, final Principal principal) {
        logger.info("Withdrawal cash ...");
        return () -> {
            // Get card number from security context
            UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
            AtmUser atmUser = (AtmUser) authenticationToken.getPrincipal();
            String cardNumber = atmUser.getUsername();
            Long atmId = atmUser.getAtmId();
            // Add validation and service calls
            WithdrawCashCommand command = new WithdrawCashCommand(atmId, cardNumber, jsonObject.getAmount());
            validate(command);
            atmService.withdraw(command);
            return ResponseEntity.ok(null);
        };
    }

    @PutMapping(value = "/transfer", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Transfer cash")
    public Callable<ResponseEntity<?>> doTransfer(@RequestBody final CashTransferJsonObject jsonObject, final Principal principal) {
        logger.info("Transfer cash ...");
        return () -> {
            // Get card number from security context
            UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
            AtmUser atmUser = (AtmUser) authenticationToken.getPrincipal();
            String cardNumber = atmUser.getUsername();
            Long atmId = atmUser.getAtmId();
            // Add validation and service calls
            TransferCashCommand command = new TransferCashCommand(atmId, jsonObject.getAmount(), cardNumber, jsonObject.getToCardNumber());
            validate(command);
            atmService.transfer(command);
            return ResponseEntity.ok(null);
        };
    }

    private void validate(Command command) {
        Assert.notNull(command, "Command should not be null.");
        BeanPropertyBindingResult results = new BeanPropertyBindingResult(command, "atm");
        atmCommandValidator.validate(command, results);
        if (results.hasErrors()) {
            throw new ValidationException(results);
        }
    }
}

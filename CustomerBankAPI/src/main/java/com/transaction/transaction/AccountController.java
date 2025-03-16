package com.transaction.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/saveAccount")
    public Account saveAccount(@RequestBody Account account){
        return accountService.saveAccount(account);
    }


    @GetMapping("/GetAllaccounts")
    public ResponseEntity<List<Account>> getAllAccountById(){
        List<Account> account = accountService.getAllAccounts();
        return ResponseEntity.ok(account);
    }

    @GetMapping("/GetAccountById/{accountId}")
    public ResponseEntity<Account> getAccountByAccountNumber(@PathVariable String accountNumber){
            Account account = accountService.findAccountsByAccountNumber( accountNumber);
            return ResponseEntity.ok(account);

    }

    @GetMapping("/GetAccountBalance/{accountNumber}")
    public BigDecimal findBalanceByAccountNumber (@PathVariable String accountNumber){
         return accountService.findBalanceByAccountNumber(accountNumber);
    }




}

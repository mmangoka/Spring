package com.transaction.transaction.Controller;

import com.transaction.transaction.Service.AccountServiceImpl;
import com.transaction.transaction.Model.Account;
import com.transaction.transaction.payLoad.APIResponse;
import com.transaction.transaction.payLoad.AccountDTO;
import com.transaction.transaction.payLoad.CustomerTransactionsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountServiceImpl accountService;

    @PostMapping("/saveAccount")
    public ResponseEntity<APIResponse> saveAccount(@RequestBody AccountDTO accountDTO){
        AccountDTO createAccountDTO = accountService.saveAccount(accountDTO);

        APIResponse<AccountDTO> response = new APIResponse<>("Transaction : Account  Creation successful",
                createAccountDTO, true );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/GetAllaccounts")
    public ResponseEntity<List<Account>> getAllAccountById(){
        List<Account> account = accountService.getAllAccounts();
        return ResponseEntity.ok(account);
    }

    @GetMapping("/GetAccountById/{account_number}")
    public ResponseEntity<Account> getAccountByAccountNumber(@PathVariable("account_number") String account_number){
           Account account = accountService.findAccountByAccountNumber( account_number);
            return new ResponseEntity<>(account , HttpStatus.OK);

    }

    @GetMapping("/GetAccountBalance/{account_number}")
    public BigDecimal findBalanceByAccountNumber (@PathVariable("account_number") String account_number){
         return accountService.findBalanceByAccountNumber(account_number);
    }




}

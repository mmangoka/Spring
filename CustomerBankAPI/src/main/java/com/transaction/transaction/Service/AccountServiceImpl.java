package com.transaction.transaction.Service;


import com.transaction.transaction.exceptions.APIExceptions;
import com.transaction.transaction.payLoad.AccountDTO;
import com.transaction.transaction.repositories.AccountRepository;
import com.transaction.transaction.Model.Account;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountServiceImpl {


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper mapper;


    public AccountDTO saveAccount(AccountDTO accountDTO){
         Account account =  mapper.map(accountDTO,Account.class);
         Account savedAccountDB = accountRepository.findAccountByAccountNumber(accountDTO.getAccountNumber());

         if(savedAccountDB != null){
             throw new APIExceptions("AccountNumber" + accountDTO.getAccountNumber() + " already exists");
         }


         Account savedAccount = accountRepository.save(account);
         return mapper.map(savedAccount,AccountDTO.class);

    }

    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    public Account findAccountByAccountNumber(String account_number)  {
        Account accountFoundNumber =  accountRepository.findAccountByAccountNumber(account_number);


        if(accountFoundNumber == null || accountFoundNumber.getAccountNumber() == null ||
        accountFoundNumber.getAccountNumber().equals("0")) {
            throw new APIExceptions("Account not found with account number: " + account_number);
        }

        return accountFoundNumber;
    }

    public BigDecimal findBalanceByAccountNumber(String accountNumber){

        Account findaccount = accountRepository.findAccountByAccountNumber(accountNumber);

        if(findaccount == null) {
            throw new APIExceptions("Account not found with account number: " + accountNumber);
        }

       BigDecimal bankBalance =  accountRepository.findBalanceByAccountNumber(accountNumber);

       if(bankBalance == null || bankBalance.compareTo(BigDecimal.ZERO) == 0){
           return BigDecimal.ZERO;
       }

       return bankBalance;

    }


}

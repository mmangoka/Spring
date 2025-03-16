package com.transaction.transaction;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountService {


    @Autowired
    private AccountRepository accountRepository;


    public Account  saveAccount(Account accountRequest){
         Account account =  new Account(accountRequest.getAccountNumber(),
            accountRequest.getOwnerName(),accountRequest.getBalance());

         return accountRepository.save(account);

    }

    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    public Account findAccountByAccountNumber(String accountNumber) throws AccountNotFoundException {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with account number: " + accountNumber));
    }

    public BigDecimal findBalanceByAccountNumber(String accountNumber){

       BigDecimal bankBalance =  accountRepository.findBalanceByAccountNumber(accountNumber);

       if(bankBalance == null || bankBalance.compareTo(BigDecimal.ZERO) == 0){
           return BigDecimal.ZERO;
       }

       return bankBalance;

    }


}

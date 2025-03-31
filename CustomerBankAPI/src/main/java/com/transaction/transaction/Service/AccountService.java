package com.transaction.transaction.Service;

import com.transaction.transaction.Model.Account;
import com.transaction.transaction.payLoad.AccountDTO;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    public AccountDTO saveAccount(AccountDTO accountDTO);
    public List<Account> getAllAccounts();
    public Account findAccountByAccountNumber(String account_number);
    public BigDecimal findBalanceByAccountNumber(String accountNumber);

}

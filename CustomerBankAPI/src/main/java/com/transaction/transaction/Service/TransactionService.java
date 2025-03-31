package com.transaction.transaction.Service;

import com.transaction.transaction.Model.CustomerTransaction;
import com.transaction.transaction.payLoad.CustomerTransactionsDTO;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {

    public CustomerTransactionsDTO depositTransaction(CustomerTransactionsDTO transactionDTO);
    public List<CustomerTransaction> getAllTransactions();
    public CustomerTransactionsDTO transferFunds(CustomerTransactionsDTO transactionDTO);

}

package com.transaction.transaction;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private CustomerTransactionRepository transactionRepository;




}

package com.transaction.transaction;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private CustomerTransactionRepository transactionRepository;



    public CustomerTransaction saveTransaction(CustomerTransaction transaction){
        return transactionRepository.save(transaction);
    }

    public List<CustomerTransaction> getAllTransactions(){
        return transactionRepository.findAll();
    }

    public CustomerTransaction getTransactionById(Long id){
        return transactionRepository.findById(id).
                orElseThrow(() ->new RuntimeException("Transaction id not found."));
    }

    public String transferFunds(String sender, String receiver, BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO) < 0){
            throw new RuntimeException("Amount must be greater than zero.");
        }

        Long idnextVal = 1L;

        CustomerTransaction senderTransaction = new CustomerTransaction(idnextVal++,sender,receiver,amount,
                "Transfer", LocalDateTime.now());

        transactionRepository.save(senderTransaction);

        return "Transer of " + amount + " from " + sender + " to " + receiver;
    }




}

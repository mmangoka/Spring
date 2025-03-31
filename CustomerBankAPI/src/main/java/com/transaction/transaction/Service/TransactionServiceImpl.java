package com.transaction.transaction.Service;


import com.transaction.transaction.Model.Account;
import com.transaction.transaction.payLoad.CustomerTransactionsDTO;
import com.transaction.transaction.repositories.AccountRepository;
import com.transaction.transaction.repositories.CustomerTransactionRepository;
import com.transaction.transaction.Model.CustomerTransaction;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl {

    @Autowired
    private CustomerTransactionRepository transactionRepository;

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private AccountRepository accountRepository;

    public CustomerTransactionsDTO depositTransaction(CustomerTransactionsDTO transactionDTO){
        CustomerTransaction transaction = mapper.map(transactionDTO,CustomerTransaction.class);
        //deduct from sender
        Account savedAccountRecipient = accountRepository.findAccountByAccountNumber(transaction.getSenderAccountNumber());

        if(savedAccountRecipient ==  null){
            throw new RuntimeException("Account not found");
        }

        BigDecimal depositAmount = savedAccountRecipient.getBalance();

        savedAccountRecipient.setBalance(depositAmount.add(transaction.getAmount()));
        CustomerTransaction savedTransaction = transactionRepository.save(transaction);
        return mapper.map(savedTransaction,CustomerTransactionsDTO.class);
    }

    public List<CustomerTransaction> getAllTransactions(){
        return transactionRepository.findAll();
    }

    public CustomerTransaction getTransactionById(Long id){
        return transactionRepository.findById(id).
                orElseThrow(() ->new RuntimeException("Transaction id not found."));
    }

    @Transactional
    public CustomerTransactionsDTO transferFunds(CustomerTransactionsDTO transactionDTO){
        CustomerTransaction transaction = mapper.map(transactionDTO,CustomerTransaction.class);

        BigDecimal savedAmount = transaction.getAmount();

        if(savedAmount.compareTo(BigDecimal.ZERO) < 0){
            throw new RuntimeException("Amount must be greater than zero.");
        }

        //fetch sender account
        Account senderAccount =  accountRepository.findAccountByAccountNumber(transaction.getSenderAccountNumber());


        if(senderAccount == null){
            throw new RuntimeException("Sender Account not found.");
        }

        //fetch receiver account
        Account receiverAccount =  accountRepository.findAccountByAccountNumber(transaction.getReceiverAccountNumber());


        if(receiverAccount == null){
            throw new RuntimeException("Receiver Account not found.");
        }

        //validate sufficient funds
        if(senderAccount.getBalance().compareTo(transaction.getAmount()) < 0){
            throw new RuntimeException("Insufficient funds in sender's account.");
        }

        //deduct from sender
        senderAccount.setBalance(senderAccount.getBalance().subtract(transaction.getAmount()));

        //add to receiver
        receiverAccount.setBalance(receiverAccount.getBalance().add(transaction.getAmount()));

        accountRepository.save(senderAccount);
        accountRepository.save(receiverAccount);

        CustomerTransaction savedTransaction =transactionRepository.save(transaction);
        return mapper.map(savedTransaction,CustomerTransactionsDTO.class);
    }


}

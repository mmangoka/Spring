package com.transaction.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class CustomerTransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/save")
    public CustomerTransaction saveTransaction(@RequestBody CustomerTransaction transaction){
          return transactionService.saveTransaction(transaction);
    }

    // Get all transactions
    @GetMapping
    public ResponseEntity<List<CustomerTransaction>> getAllTransactions() {
        List<CustomerTransaction> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    // Get a transaction by ID
    @GetMapping("/{id}")
    public ResponseEntity<CustomerTransaction> getTransactionById(@PathVariable Long id) {
        CustomerTransaction transaction = transactionService.getTransactionById(id);
        return ResponseEntity.ok(transaction);
    }


  }

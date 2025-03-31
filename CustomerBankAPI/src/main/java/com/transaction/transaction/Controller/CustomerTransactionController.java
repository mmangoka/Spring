package com.transaction.transaction.Controller;

import com.transaction.transaction.Model.CustomerTransaction;
import com.transaction.transaction.Service.TransactionServiceImpl;
import com.transaction.transaction.payLoad.APIResponse;
import com.transaction.transaction.payLoad.CustomerTransactionsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class CustomerTransactionController {

    @Autowired
    private TransactionServiceImpl transactionService;

    @PostMapping("/Deposit")
    public ResponseEntity<APIResponse> depositTransaction(@RequestBody CustomerTransactionsDTO transactionDTO){
        CustomerTransactionsDTO savedTransaction = transactionService.depositTransaction(transactionDTO);
        APIResponse<CustomerTransactionsDTO> response = new APIResponse<>("Transaction : Deposit  of funds operation successful",
                savedTransaction, true );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Get all transactions
    @GetMapping("/GetAllTransactions")
    public ResponseEntity<List<CustomerTransaction>> getAllTransactions() {
        List<CustomerTransaction> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    // Get a transaction by ID
    @GetMapping("/GetTransactionByID/{id}")
    public ResponseEntity<CustomerTransaction> getTransactionById(@PathVariable Long id) {
        CustomerTransaction transaction = transactionService.getTransactionById(id);
        return ResponseEntity.ok(transaction);
    }


    @PostMapping("/transferFunds")
    public ResponseEntity<APIResponse> transferFunds(@RequestBody CustomerTransactionsDTO transactionDTO){
        CustomerTransactionsDTO savedTransaction = transactionService.transferFunds(transactionDTO);

        APIResponse<CustomerTransactionsDTO> response = new APIResponse<>("Transaction : Transfer of funds operation successful",
                savedTransaction, true );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

  }

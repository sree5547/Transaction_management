package com.acabes.transfer.controller;

import com.acabes.transfer.dto.AccountTransferResponseDTO;
import com.acabes.transfer.dto.CreateTransactionDTO;
import com.acabes.transfer.dto.TransactionStatusResponseDTO;
import com.acabes.transfer.entity.Transaction;
import com.acabes.transfer.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    // Create a new transaction (handles debit + credit)
    @PostMapping("/create")
    public ResponseEntity<AccountTransferResponseDTO> createTransaction(@RequestBody CreateTransactionDTO createTransactionDTO) {
        log.info("Received request to create transaction: {}", createTransactionDTO);
        AccountTransferResponseDTO created = transactionService.createTransaction(createTransactionDTO);
        return ResponseEntity.ok(created);
    }


    // Fetch the status of a transaction
    @GetMapping("/status/{id}")
    public ResponseEntity<TransactionStatusResponseDTO> fetchTransactionStatus(@PathVariable String id) {
        TransactionStatusResponseDTO responseDTO = transactionService.transactionStatusService(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }

    @GetMapping("/balance/{id}")
    public ResponseEntity<BigDecimal> fetchAccountBalance(@PathVariable String id) {
        BigDecimal balance = transactionService.getAccountBalanceService(id);
        return new ResponseEntity<>(balance,HttpStatus.OK);
    }


    // Get transaction history for a given account
    @GetMapping("/history/{accountNumber}")
    public ResponseEntity<List<Transaction>> getTransactionHistory(@PathVariable String accountNumber) {
        List<Transaction> transactions = transactionService.getTransactionHistory(accountNumber);
        return ResponseEntity.ok(transactions);
    }
}

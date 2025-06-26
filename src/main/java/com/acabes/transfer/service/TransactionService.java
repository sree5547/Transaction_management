package com.acabes.transfer.service;


import com.acabes.transfer.dto.AccountTransferResponseDTO;
import com.acabes.transfer.dto.CreateTransactionDTO;
import com.acabes.transfer.dto.TransactionStatusResponseDTO;
import com.acabes.transfer.entity.Transaction;
import com.acabes.transfer.entity.TransactionStatus;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {

    AccountTransferResponseDTO createTransaction(CreateTransactionDTO transaction);

    Transaction updateTransactionStatus(Long id, TransactionStatus status);

    TransactionStatusResponseDTO transactionStatusService(String id);

    List<Transaction> getTransactionHistory(String accountNumber);

    BigDecimal getAccountBalanceService(String id);
}

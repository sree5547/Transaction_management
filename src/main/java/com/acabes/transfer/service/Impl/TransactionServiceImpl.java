package com.acabes.transfer.service.Impl;


import com.acabes.transfer.client.AccountClient;
import com.acabes.transfer.dto.*;
import com.acabes.transfer.entity.Transaction;
import com.acabes.transfer.entity.TransactionStatus;
import com.acabes.transfer.exception.ResourceNotFoundException;
import com.acabes.transfer.repository.TransactionRepository;
import com.acabes.transfer.service.TransactionService;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {


    @Autowired
    private AccountClient accountClient;
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public AccountTransferResponseDTO createTransaction(CreateTransactionDTO createTransactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setTimestamp(Instant.now());

        transaction.setSenderAccount(createTransactionDTO.getSenderAccount());
        transaction.setReceiverAccount(createTransactionDTO.getReceiverAccount());
        transaction.setAmount(createTransactionDTO.getAmount());
        transaction.setRemark(createTransactionDTO.getRemark());

        try {

            transaction.setStatus(TransactionStatus.PENDING);
            transactionRepository.save(transaction);

            AccountTransferRequestDTO requestDTO = new AccountTransferRequestDTO();
            requestDTO.setAmount(transaction.getAmount());
            requestDTO.setToAccountId(transaction.getReceiverAccount());
            requestDTO.setFromAccountId(transaction.getSenderAccount());


            accountClient.transferAmount(requestDTO);
            log.info("Transfer successful for transaction {}", transaction.getId());
            transaction.setStatus(TransactionStatus.SUCCESS);

        } catch (FeignException e) {

            log.error("Transaction {} failed. Feign client call to account-service failed with status {} and body: {}",
                    transaction.getId(), e.status(), e.contentUTF8(), e);

            throw new RuntimeException(e.contentUTF8());
        } catch (Exception e) {

            log.error("An unexpected error occurred during transaction {}: {}", transaction.getId(), e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }



        transactionRepository.save(transaction);

        AccountTransferResponseDTO responseDTO = new AccountTransferResponseDTO();
        responseDTO.setTransactionId(transaction.getId());
        responseDTO.setResponseMessage("The transaction was successful");
        responseDTO.setStatus(transaction.getStatus());

        return responseDTO;

    }

    @Override
    public Transaction updateTransactionStatus(Long id, TransactionStatus status) {
        return null;
    }

    @Override
    public TransactionStatusResponseDTO transactionStatusService(String id) {

        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with ID: " + id));


        TransactionStatusResponseDTO responseDTO = new TransactionStatusResponseDTO();


        responseDTO.setId(transaction.getId());
        responseDTO.setStatus(transaction.getStatus());

        return responseDTO;
    }


    @Override
    public List<Transaction> getTransactionHistory(String accountNumber) {
        try{
            List<Transaction> transactionList = transactionRepository.findBySenderAccountOrReceiverAccount(accountNumber,accountNumber);
            return transactionList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BigDecimal getAccountBalanceService(String id) {

        try{
            AccountDetailsResponseDTO responseDTO = accountClient.getAccountDetails(id);
            return responseDTO.getBalance();

        }catch(FeignException e){
            throw new ResourceNotFoundException(e.contentUTF8());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}

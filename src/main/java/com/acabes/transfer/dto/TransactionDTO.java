package com.acabes.transfer.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionDTO {
    private String fromAccountId;
    private String toAccountId;
    private BigDecimal amount;

    public TransactionDTO(){

    }

    public TransactionDTO(CreateTransactionDTO request){
        this.fromAccountId = request.getSenderAccount();
        this.toAccountId = request.getReceiverAccount();
        this.amount = request.getAmount();
    }
}

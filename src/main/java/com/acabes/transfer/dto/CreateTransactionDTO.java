package com.acabes.transfer.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateTransactionDTO {
    private String senderAccount;
    private  String receiverAccount;
    private BigDecimal amount;
    private String currency;
    private String remark;


}

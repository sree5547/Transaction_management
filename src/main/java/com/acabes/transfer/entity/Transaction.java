package com.acabes.transfer.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Document(collection = "transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    private String id;
    private String senderAccount;
    private  String receiverAccount;
    private String currency;
    private BigDecimal amount;

    private Instant timestamp;
    private TransactionStatus status;
    private String remark;


}

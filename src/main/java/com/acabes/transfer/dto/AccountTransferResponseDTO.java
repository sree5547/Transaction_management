package com.acabes.transfer.dto;

import com.acabes.transfer.entity.TransactionStatus;
import lombok.Data;

@Data
public class AccountTransferResponseDTO {

    private String transactionId;
    private TransactionStatus status;
    private String responseMessage;

}

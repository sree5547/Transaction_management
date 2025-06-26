package com.acabes.transfer.dto;

import com.acabes.transfer.entity.TransactionStatus;
import lombok.Data;

@Data
public class TransactionStatusResponseDTO {
    private String id;
    private TransactionStatus status;
}

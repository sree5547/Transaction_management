package com.acabes.transfer.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountTransferRequestDTO {
    String fromAccountId;
    String toAccountId;
    BigDecimal amount;
}

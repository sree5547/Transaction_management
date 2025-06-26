package com.acabes.transfer.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDetailsResponseDTO {
    String accountId;
    String customerId;
    String accountTypeId;
    String accountTypeName;
    String branchId;
    String branchName;
    String status;
    BigDecimal balance;
}

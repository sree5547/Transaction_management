package com.acabes.transfer.client;

import com.acabes.transfer.dto.AccountDetailsResponseDTO;
import com.acabes.transfer.dto.TransactionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
@FeignClient(
        name = "account-service",
        url = "https://tadpole-closing-prawn.ngrok-free.app",
        configuration = com.acabes.transfer.configurations.FeignCustomConfig.class
)
public interface AccountClient {

    @PostMapping("/api/accounts/transfer")
    String transferAmount(@RequestBody TransactionDTO requestDTO);

    @GetMapping("/api/accounts/{accountId}")
    AccountDetailsResponseDTO getAccountDetails(@PathVariable String accountId);
}

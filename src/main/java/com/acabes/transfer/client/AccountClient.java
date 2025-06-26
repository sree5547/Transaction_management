package com.acabes.transfer.client;

import com.acabes.transfer.dto.AccountDetailsResponseDTO;
import com.acabes.transfer.dto.AccountTransferRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@FeignClient(name = "account-service", url = "https://831d-111-92-81-182.ngrok-free.app/api/accounts")
public interface AccountClient {



    @PostMapping("/transfer")
    String transferAmount(@RequestBody AccountTransferRequestDTO requestDTO);

    @GetMapping("/{accountId}")
    AccountDetailsResponseDTO getAccountDetails(@PathVariable String accountId);
}


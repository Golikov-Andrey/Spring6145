package com.example.controllers;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.TransferRequest;
import com.example.model.Account;
import com.example.services.TransferService;

@AllArgsConstructor
@RestController
public class AccountController {

  private final TransferService transferService;

  @PostMapping("/transfer")
  public void transferMoney(
      @RequestBody TransferRequest request
      ) {
    transferService.transferMoney(
        request.getSenderAccountId(),
        request.getReceiverAccountId(),
        request.getAmount());
  }

  @GetMapping("/accounts")
  public List<Account> getAllAccounts() {
    return transferService.getAllAccounts();
  }
}

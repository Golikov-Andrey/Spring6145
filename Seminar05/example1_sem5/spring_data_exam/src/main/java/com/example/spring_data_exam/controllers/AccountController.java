package com.example.spring_data_exam.controllers;


import com.example.spring_data_exam.dto.TransferRequest;
import com.example.spring_data_exam.model.Account;
import com.example.spring_data_exam.services.TransferService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
  public Iterable<Account> getAllAccounts(
      @RequestParam(required = false) String name
  ) {
    if (name == null) {
      return transferService.getAllAccounts();
    } else {
      return transferService.findAccountsByName(name);
    }
  }

}

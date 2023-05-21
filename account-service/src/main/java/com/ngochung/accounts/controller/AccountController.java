package com.ngochung.accounts.controller;

import com.ngochung.accounts.entity.Account;
import com.ngochung.accounts.entity.Customer;
import com.ngochung.accounts.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

  private final AccountService accountService;

  @Autowired
  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @GetMapping
  public Account getAccountDetails(@RequestBody Customer customer) {

    Account accounts = accountService.getAccountByCustomer(customer.getId());
    if (accounts != null) {
      return accounts;
    } else {
      return null;
    }
  }
}

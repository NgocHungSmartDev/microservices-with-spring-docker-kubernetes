package com.ngochung.accounts.service.impl;

import com.ngochung.accounts.entity.Account;
import com.ngochung.accounts.repository.AccountRepository;
import com.ngochung.accounts.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
  private final AccountRepository accountRepository;

  @Autowired
  public AccountServiceImpl(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Override
  public Account getAccountByCustomer(long customerId) {
    return null;
  }
}

package com.ngochung.accounts.service;

import com.ngochung.accounts.entity.Account;

public interface AccountService {
  Account getAccountByCustomer(long customerId);
}

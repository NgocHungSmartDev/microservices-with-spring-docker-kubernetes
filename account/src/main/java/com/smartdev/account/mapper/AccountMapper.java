package com.smartdev.account.mapper;

import com.smartdev.account.dto.AccountDto;
import com.smartdev.account.entity.Account;

public class AccountMapper {
  public static AccountDto mapToAccountDto(Account account, AccountDto accountDto) {
    accountDto.setAccountNumber(account.getAccountNumber());
    accountDto.setAccountType(account.getAccountType());
    accountDto.setBranchAddress(account.getBranchAddress());
    return accountDto;
  }

  public static Account mapToAccount(AccountDto AccountDto, Account Account) {
    Account.setAccountNumber(AccountDto.getAccountNumber());
    Account.setAccountType(AccountDto.getAccountType());
    Account.setBranchAddress(AccountDto.getBranchAddress());
    return Account;
  }
}

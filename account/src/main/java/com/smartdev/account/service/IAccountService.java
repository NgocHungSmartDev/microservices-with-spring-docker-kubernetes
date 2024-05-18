package com.smartdev.account.service;

import com.smartdev.account.dto.CustomerDto;

import java.util.List;

public interface IAccountService {
  void createAccount(CustomerDto customerDto);

  CustomerDto fetchAccount(String mobileNumber);

  List<CustomerDto> fetchAccounts();

  boolean updateAccount(CustomerDto customerDto);

  boolean deleteAccount(String mobileNumber);
}

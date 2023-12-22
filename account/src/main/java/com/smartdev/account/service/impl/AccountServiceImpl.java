package com.smartdev.account.service.impl;

import com.smartdev.account.constants.AccountConstants;
import com.smartdev.account.dto.AccountDto;
import com.smartdev.account.dto.CustomerDto;
import com.smartdev.account.entity.Account;
import com.smartdev.account.entity.Customer;
import com.smartdev.account.exception.CustomerAlreadyExistsException;
import com.smartdev.account.exception.ResourceNotFoundException;
import com.smartdev.account.mapper.AccountMapper;
import com.smartdev.account.mapper.CustomerMapper;
import com.smartdev.account.repository.AccountRepository;
import com.smartdev.account.repository.CustomerRepository;
import com.smartdev.account.service.IAccountService;
import java.util.Optional;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {
  private AccountRepository AccountRepository;
  private CustomerRepository customerRepository;

  @Override
  public void createAccount(CustomerDto customerDto) {
    Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
    Optional<Customer> optionalCustomer =
        customerRepository.findByMobileNumber(customerDto.getMobileNumber());
    if (optionalCustomer.isPresent()) {
      throw new CustomerAlreadyExistsException(
          "Customer already registered with given mobileNumber " + customerDto.getMobileNumber());
    }
    Customer savedCustomer = customerRepository.save(customer);
    AccountRepository.save(createNewAccount(savedCustomer));
  }

  private Account createNewAccount(Customer customer) {
    Account newAccount = new Account();
    newAccount.setCustomerId(customer.getCustomerId());
    long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

    newAccount.setAccountNumber(randomAccNumber);
    newAccount.setAccountType(AccountConstants.SAVINGS);
    newAccount.setBranchAddress(AccountConstants.ADDRESS);
    return newAccount;
  }

  @Override
  public CustomerDto fetchAccount(String mobileNumber) {
    Customer customer =
        customerRepository
            .findByMobileNumber(mobileNumber)
            .orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
    Account account =
        AccountRepository.findByCustomerId(customer.getCustomerId())
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        "Account", "customerId", customer.getCustomerId().toString()));
    CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
    customerDto.setAccountDto(AccountMapper.mapToAccountDto(account, new AccountDto()));
    return customerDto;
  }

  @Override
  public boolean updateAccount(CustomerDto customerDto) {
    boolean isUpdated = false;
    AccountDto AccountDto = customerDto.getAccountDto();
    if (AccountDto != null) {
      Account Account =
          AccountRepository.findById(AccountDto.getAccountNumber())
              .orElseThrow(
                  () ->
                      new ResourceNotFoundException(
                          "Account", "AccountNumber", AccountDto.getAccountNumber().toString()));
      AccountMapper.mapToAccount(AccountDto, Account);
      Account = AccountRepository.save(Account);

      Long customerId = Account.getCustomerId();
      Customer customer =
          customerRepository
              .findById(customerId)
              .orElseThrow(
                  () ->
                      new ResourceNotFoundException(
                          "Customer", "CustomerID", customerId.toString()));
      CustomerMapper.mapToCustomer(customerDto, customer);
      customerRepository.save(customer);
      isUpdated = true;
    }
    return isUpdated;
  }

  @Override
  public boolean deleteAccount(String mobileNumber) {
    Customer customer =
        customerRepository
            .findByMobileNumber(mobileNumber)
            .orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
    AccountRepository.deleteByCustomerId(customer.getCustomerId());
    customerRepository.deleteById(customer.getCustomerId());
    return true;
  }
}

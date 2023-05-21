package com.ngochung.accounts.repository;

import com.ngochung.accounts.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
  Account findByCustomerId(long customerId);
}

package com.smartdev.account.repository;

import com.smartdev.account.entity.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface AccountRepository extends JpaRepository<Account, Long> {
  Optional<Account> findByCustomerId(Long customerId);

  @Modifying
  void deleteByCustomerId(Long customerId);
}

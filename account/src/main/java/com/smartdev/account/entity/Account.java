package com.smartdev.account.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class Account extends BaseEntity {
  @Id
  @Column(name = "account_number")
  private Long accountNumber;

  @Column(name = "customer_id")
  private Long customerId;

  @Column(name = "account_type")
  private String accountType;

  @Column(name = "branch_address")
  private String branchAddress;
}

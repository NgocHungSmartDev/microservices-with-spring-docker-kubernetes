package com.smartdev.account.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class Customer extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name = "native", strategy = "native")
  @Column(name = "customer_id")
  private Long customerId;

  private String name;

  private String email;

  @Column(name = "mobile_number")
  private String mobileNumber;
}

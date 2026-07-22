package com.autowashpro.repository;


import com.autowashpro.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByAccount_Phone(String phone);
    Optional<Customer> findByAccount_Email(String email);
    Optional<Customer> findByAccount_Username(String username);
    List<Customer> findByTier_TierName(String tierName);
    boolean existsByTier_Id(Integer tierId);
}

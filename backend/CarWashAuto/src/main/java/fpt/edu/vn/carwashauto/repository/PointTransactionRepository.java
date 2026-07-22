package com.autowashpro.repository;

import com.autowashpro.entity.PointTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointTransactionRepository extends JpaRepository<PointTransaction, Integer> {
    List<PointTransaction> findByCustomer_IdOrderByCreatedAtDesc(Integer customerId);
    List<PointTransaction> findByCustomer_IdAndTransactionType(Integer customerId, String type);
}

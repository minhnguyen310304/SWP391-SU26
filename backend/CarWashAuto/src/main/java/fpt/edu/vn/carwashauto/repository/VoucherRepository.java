package com.autowashpro.repository;

import com.autowashpro.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Integer> {
    List<Voucher> findByCustomer_Id(Integer customerId);
    Optional<Voucher> findByVoucherCode(String voucherCode);
    List<Voucher> findByCustomer_IdAndStatus(Integer customerId, String status);
}

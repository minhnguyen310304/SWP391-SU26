package com.autowashpro.repository;

import com.autowashpro.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    Optional<Invoice> findByBooking_Id(Integer bookingId);
    List<Invoice> findByPaymentStatus(String paymentStatus);
}

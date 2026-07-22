package com.autowashpro.repository;

import com.autowashpro.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Optional<Payment> findByBooking_Id(Integer bookingId);
    List<Payment> findAllByTransactionRefOrderByIdAsc(String transactionRef);

    @Query(value = """
        SELECT COALESCE(SUM(p.amount), 0) FROM Payment p
        WHERE p.status = 'SUCCESS'
          AND p.paymentDate BETWEEN :fromDate AND :toDate
    """)
    BigDecimal sumSuccessfulPaymentAmountBetween(
            @Param("fromDate") LocalDateTime fromDate,
            @Param("toDate") LocalDateTime toDate
    );

    @Query(value = """
        SELECT COALESCE(SUM(p.discountAmount), 0) FROM Payment p
        WHERE p.status = 'SUCCESS'
          AND p.paymentDate BETWEEN :fromDate AND :toDate
    """)
    BigDecimal sumDiscountAmountBetween(
            @Param("fromDate") LocalDateTime fromDate,
            @Param("toDate") LocalDateTime toDate
    );
}

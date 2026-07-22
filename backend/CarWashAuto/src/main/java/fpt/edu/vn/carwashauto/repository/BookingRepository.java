package com.autowashpro.repository;

import com.autowashpro.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByCustomer_Id(Integer customerId);
    List<Booking> findByStaff_Id(Integer staffId);
    List<Booking> findByStaff_IdAndBookingDate(Integer staffId, LocalDate bookingDate);
    List<Booking> findByStaff_IdAndBookingDateBetween(Integer staffId, LocalDate fromDate, LocalDate toDate);
    List<Booking> findByBookingDate(LocalDate bookingDate);
    List<Booking> findByStatus(String status);
    List<Booking> findByCustomer_IdAndStatus(Integer customerId, String status);

    @Query(value = """
        SELECT COUNT(1)
        FROM bookings b
        WHERE CAST(b.booking_date AS date) = CAST(:date AS date)
          AND b.status IN ('PENDING', 'WORKING')
          AND CAST(b.start_time AS time) < CAST(:endTime AS time)
          AND CAST(b.end_time AS time) > CAST(:startTime AS time)
    """, nativeQuery = true)
    int countOverlappingBookings(
            @Param("date") LocalDate date,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime
    );

    List<Booking> findByBookingDateBetween(LocalDate fromDate, LocalDate toDate);

    @Query(value = """
        SELECT COUNT(b) FROM Booking b
        WHERE b.bookingDate BETWEEN :fromDate AND :toDate
    """)
    long countByBookingDateBetween(@Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);

    @Query(value = """
        SELECT COUNT(b) FROM Booking b
        WHERE b.bookingDate BETWEEN :fromDate AND :toDate AND b.status = :status
    """)
    long countByBookingDateBetweenAndStatus(@Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate, @Param("status") String status);
}

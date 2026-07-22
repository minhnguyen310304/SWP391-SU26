package com.autowashpro.repository;

import com.autowashpro.entity.BookingService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingServiceRepository extends JpaRepository<BookingService, Integer> {
    List<BookingService> findByBooking_Id(Integer bookingId);
}

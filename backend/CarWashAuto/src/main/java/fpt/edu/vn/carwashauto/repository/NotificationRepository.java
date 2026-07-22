package com.autowashpro.repository;

import com.autowashpro.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    List<Notification> findByStaffIdOrderByCreatedAtDesc(Integer staffId);

    long countByStaffIdAndIsReadFalse(Integer staffId);

    void deleteByBookingId(Integer bookingId);
}

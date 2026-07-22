package com.autowashpro.repository;

import com.autowashpro.entity.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Integer> {
    List<LeaveRequest> findByStatus(String status);
    List<LeaveRequest> findByStaff_Id(Integer staffId);
}

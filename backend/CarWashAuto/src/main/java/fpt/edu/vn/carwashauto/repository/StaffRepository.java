package com.autowashpro.repository;


import com.autowashpro.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
    Optional<Staff> findByAccount_Phone(String phone);
    List<Staff> findByStatus(String status);
}

package com.autowashpro.repository;


import com.autowashpro.entity.StaffShift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StaffShiftRepository extends JpaRepository<StaffShift, Integer> {
    List<StaffShift> findByStaff_Id(Integer staffId);
    List<StaffShift> findByWorkDate(LocalDate workDate);
    List<StaffShift> findByStaff_IdAndWorkDate(Integer staffId, LocalDate workDate);
}

package com.autowashpro.repository;


import com.autowashpro.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    List<Vehicle> findByCustomer_Id(Integer customerId);
    Optional<Vehicle> findByLicensePlate(String licensePlate);

    boolean existsVehicleByLicensePlate(String plateNumber);
}

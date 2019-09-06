package com.cardealership.dealership.Cars;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarsRepository extends JpaRepository<Car, Long> {
    List<Car> findByLocation_Id(Long location_id);
    Optional<Car> findById(Long car_id);

}

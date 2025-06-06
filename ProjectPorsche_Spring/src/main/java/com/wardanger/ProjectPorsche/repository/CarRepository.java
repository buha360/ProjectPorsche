package com.wardanger.ProjectPorsche.repository;

import com.wardanger.ProjectPorsche.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByBrand(String brand);
    List<Car> findByCustomerId(Long customerId);
}

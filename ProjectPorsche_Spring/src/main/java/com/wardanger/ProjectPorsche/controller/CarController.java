package com.wardanger.ProjectPorsche.controller;

import com.wardanger.ProjectPorsche.model.Car;
import com.wardanger.ProjectPorsche.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarRepository carRepository;

    @GetMapping("/by-customer/{customerId}")
    public ResponseEntity<List<Car>> getCarsByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(carRepository.findByCustomerId(customerId));
    }

    @GetMapping("/by-brand/{brand}")
    public ResponseEntity<List<Car>> getCarsByBrand(@PathVariable String brand) {
        return ResponseEntity.ok(carRepository.findByBrand(brand));
    }
}
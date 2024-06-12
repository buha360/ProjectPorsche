package com.wardanger.ProjectPorsche.controller;

import com.wardanger.ProjectPorsche.model.Car;
import com.wardanger.ProjectPorsche.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping("/{id}")
    public Car getCarById(@PathVariable String id) {
        return carService.getCarById(id);
    }

    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return carService.saveCar(car);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable String id) {
        carService.deleteCar(id);
    }

    @PutMapping("/{id}")
    public Car updateCar(@PathVariable String id, @RequestBody Car car) {
        return carService.updateCar(id, car);
    }

    @GetMapping
    public List<Car> getAllCars(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return carService.getAllCars(page, size);
    }
}

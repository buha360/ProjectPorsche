package com.wardanger.ProjectPorsche.controller;

import com.wardanger.ProjectPorsche.model.Car;
import com.wardanger.ProjectPorsche.model.User;
import com.wardanger.ProjectPorsche.repository.CarRepository;
import com.wardanger.ProjectPorsche.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;
    private final CarRepository carRepository;

    @Autowired
    public CarController (CarService carService, CarRepository carRepository) {
        this.carService = carService;
        this.carRepository = carRepository;
    }

    @PostMapping
    public Car createCar(@RequestBody Car car) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        car.setUser(currentUser);

        return carService.saveCar(car);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        Car car = carService.getCarById(id);

        if (!car.getUser().getId().equals(currentUser.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not authorized to delete this car");
        }

        carService.deleteCar(id);
    }

    @PutMapping("/{id}")
    public Car updateCar(@PathVariable Long id, @RequestBody Car carDetails) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        Car car = carService.getCarById(id);

        if (!car.getUser().getId().equals(currentUser.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not authorized to update this car");
        }

        car.setMake(carDetails.getMake());
        car.setModel(carDetails.getModel());
        car.setYear(carDetails.getYear());

        return carService.updateCar(id, car);
    }

    @GetMapping("/my-cars")
    public List<Car> getAllCarsForUser() {
        return carService.getAllCarsForUser();
    }

    public Car checkCarOwnership(Long carId, User currentUser) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new RuntimeException("Car not found"));

        if (!car.getUser().getId().equals(currentUser.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not authorized to access this car");
        }

        return car;
    }
}

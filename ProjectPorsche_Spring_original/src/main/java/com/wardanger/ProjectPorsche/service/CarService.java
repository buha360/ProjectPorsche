package com.wardanger.ProjectPorsche.service;

import com.wardanger.ProjectPorsche.model.Car;
import com.wardanger.ProjectPorsche.model.User;
import com.wardanger.ProjectPorsche.repository.CarRepository;
import com.wardanger.ProjectPorsche.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    private final UserRepository userRepository;

    @Autowired
    public CarService (CarRepository carRepository, UserRepository userRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    public Car updateCar(Long id, Car car) {
        Car existingCar = carRepository.findById(id).orElse(null);
        if (existingCar != null) {
            existingCar.setMake(car.getMake());
            existingCar.setModel(car.getModel());
            existingCar.setYear(car.getYear());

            if (car.getUser() != null && car.getUser().getId() != null) {
                userRepository.findById(car.getUser().getId()).ifPresent(existingCar::setUser);
            }

            return carRepository.save(existingCar);
        }
        return null;
    }

    public List<Car> getAllCars(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return carRepository.findAll(pageable).getContent();
    }

    public List<Car> getAllCarsForUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return carRepository.findByUserId(user.getId());
    }

    public Car getCarById(Long carId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        Car car = carRepository.findById(carId).orElseThrow(() -> new RuntimeException("Car not found"));

        if (!car.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("Unauthorized access to this car");
        }
        return car;
    }

    public Car checkCarOwnership(Long carId, User currentUser) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new RuntimeException("Car not found"));

        if (!car.getUser().getId().equals(currentUser.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not authorized to access this car");
        }

        return car;
    }
}

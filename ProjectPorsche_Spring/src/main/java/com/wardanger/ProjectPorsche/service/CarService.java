package com.wardanger.ProjectPorsche.service;

import com.wardanger.ProjectPorsche.model.Car;
import com.wardanger.ProjectPorsche.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCarById(String id) {
        return carRepository.findById(id).orElse(null);
    }

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public void deleteCar(String id) {
        carRepository.deleteById(id);
    }

    public Car updateCar(String id, Car car) {
        Car existingCar = carRepository.findById(id).orElse(null);
        if (existingCar != null) {
            existingCar.setMake(car.getMake());
            existingCar.setModel(car.getModel());
            existingCar.setYear(car.getYear());
            return carRepository.save(existingCar);
        }
        return null;
    }

    public List<Car> getAllCars(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return carRepository.findAll(pageable).getContent();
    }
}

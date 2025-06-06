package com.wardanger.ProjectPorsche.controller;

import com.wardanger.ProjectPorsche.model.Car;
import com.wardanger.ProjectPorsche.model.ServiceRecord;
import com.wardanger.ProjectPorsche.model.User;
import com.wardanger.ProjectPorsche.repository.CarRepository;
import com.wardanger.ProjectPorsche.service.CarService;
import com.wardanger.ProjectPorsche.service.ServiceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/service-records")
public class ServiceRecordController {

    private final ServiceRecordService serviceRecordService;
    private final CarService carService; // Use CarService instead of CarController

    @Autowired
    public ServiceRecordController(ServiceRecordService serviceRecordService, CarService carService) {
        this.serviceRecordService = serviceRecordService;
        this.carService = carService; // Inject the CarService
    }

    @GetMapping("/{id}")
    public Optional<ServiceRecord> getServiceRecordById(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        carService.checkCarOwnership(id, currentUser);

        return serviceRecordService.getServiceRecordById(id);
    }

    @GetMapping("/car/{carId}")
    public List<ServiceRecord> getServiceRecordsByCarId(@PathVariable Long carId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        carService.checkCarOwnership(carId, currentUser);

        return serviceRecordService.getServiceRecordsByCarId(carId);
    }

    @PutMapping("/{id}")
    public ServiceRecord updateServiceRecord(@PathVariable Long id, @RequestBody ServiceRecord serviceRecord) {
        return serviceRecordService.getServiceRecordById(id)
                .map(existingRecord -> {
                    existingRecord.setPrice(serviceRecord.getPrice());
                    existingRecord.setServiceDate(serviceRecord.getServiceDate());
                    existingRecord.setDescription(serviceRecord.getDescription());
                    return serviceRecordService.saveServiceRecord(existingRecord);
                })
                .orElseThrow(() -> new RuntimeException("Service record not found with id " + id));
    }

    @PostMapping
    public ServiceRecord createServiceRecord(@RequestBody ServiceRecord serviceRecord) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        Long carId = serviceRecord.getCar().getId();
        Car car = carService.checkCarOwnership(carId, currentUser);

        serviceRecord.setCar(car);

        return serviceRecordService.saveServiceRecord(serviceRecord);
    }

    @DeleteMapping("/{id}")
    public void deleteServiceRecord(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        ServiceRecord serviceRecord = serviceRecordService.getServiceRecordById(id)
                .orElseThrow(() -> new RuntimeException("Service record not found with id " + id));

        Long carId = serviceRecord.getCar().getId();
        carService.checkCarOwnership(carId, currentUser);

        serviceRecordService.deleteServiceRecord(id);
    }
}
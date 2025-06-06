package com.wardanger.ProjectPorsche.service;

import com.wardanger.ProjectPorsche.model.Car;
import com.wardanger.ProjectPorsche.model.ServiceRecord;
import com.wardanger.ProjectPorsche.model.User;
import com.wardanger.ProjectPorsche.repository.CarRepository;
import com.wardanger.ProjectPorsche.repository.ServiceRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceRecordService {

    private final ServiceRecordRepository serviceRecordRepository;
    private final CarRepository carRepository;

    @Autowired
    public ServiceRecordService (ServiceRecordRepository serviceRecordRepository, CarRepository carRepository) {
        this.serviceRecordRepository = serviceRecordRepository;
        this.carRepository = carRepository;
    }

    public List<ServiceRecord> getAllServiceRecords() {
        return serviceRecordRepository.findAll();
    }

    public Optional<ServiceRecord> getServiceRecordById(Long id) {
        return serviceRecordRepository.findById(id);
    }

    public ServiceRecord saveServiceRecord(ServiceRecord serviceRecord) {
        return serviceRecordRepository.save(serviceRecord);
    }

    public void deleteServiceRecord(Long id) {
        serviceRecordRepository.deleteById(id);
    }

    public List<ServiceRecord> getServiceRecordsByCarId(Long carId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        Car car = carRepository.findById(carId).orElseThrow(() -> new RuntimeException("Car not found"));

        if (!car.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("Unauthorized access to this car's service records");
        }

        return serviceRecordRepository.findByCarId(carId);
    }
}

package com.wardanger.ProjectPorsche.controller;

import com.wardanger.ProjectPorsche.model.ServiceRecord;
import com.wardanger.ProjectPorsche.service.ServiceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/service-records")
public class ServiceRecordController {

    @Autowired
    private ServiceRecordService serviceRecordService;

    @GetMapping
    public List<ServiceRecord> getAllServiceRecords() {
        return serviceRecordService.getAllServiceRecords();
    }

    @GetMapping("/{id}")
    public Optional<ServiceRecord> getServiceRecordById(@PathVariable Long id) {
        return serviceRecordService.getServiceRecordById(id);
    }

    @GetMapping("/car/{carId}")
    public List<ServiceRecord> getServiceRecordsByCarId(@PathVariable Long carId) {
        return serviceRecordService.getServiceRecordsByCarId(carId);
    }

    @PostMapping
    public ServiceRecord createServiceRecord(@RequestBody ServiceRecord serviceRecord) {
        return serviceRecordService.saveServiceRecord(serviceRecord);
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

    @DeleteMapping("/{id}")
    public void deleteServiceRecord(@PathVariable Long id) {
        serviceRecordService.deleteServiceRecord(id);
    }
}

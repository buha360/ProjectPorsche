package com.wardanger.ProjectPorsche.service;

import com.wardanger.ProjectPorsche.model.ServiceRecord;
import com.wardanger.ProjectPorsche.repository.ServiceRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceRecordService {

    @Autowired
    private ServiceRecordRepository serviceRecordRepository;

    public List<ServiceRecord> getAllServiceRecords() {
        return serviceRecordRepository.findAll();
    }

    public List<ServiceRecord> getServiceRecordsByCarId(Long carId) {
        return serviceRecordRepository.findByCarId(carId);
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
}

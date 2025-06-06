package com.wardanger.ProjectPorsche.repository;

import com.wardanger.ProjectPorsche.model.ServiceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRecordRepository extends JpaRepository<ServiceRecord, Long> {
    List<ServiceRecord> findByCarId(Long carId);
}

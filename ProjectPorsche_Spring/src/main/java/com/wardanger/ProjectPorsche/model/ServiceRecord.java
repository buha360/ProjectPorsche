package com.wardanger.ProjectPorsche.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "service_records")
public class ServiceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id")
    @JsonBackReference // Ez megakadályozza a végtelen ciklust
    private Car car;

    private BigDecimal price;
    private LocalDate serviceDate;
    private String description;
}

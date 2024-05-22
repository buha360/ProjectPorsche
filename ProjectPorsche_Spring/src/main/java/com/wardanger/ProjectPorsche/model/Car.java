package com.wardanger.ProjectPorsche.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "cars")
public class Car {
    @Id
    private String id;
    private String make;
    private String model;
    private int year;
}

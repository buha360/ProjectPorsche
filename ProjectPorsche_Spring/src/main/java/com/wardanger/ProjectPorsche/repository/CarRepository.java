package com.wardanger.ProjectPorsche.repository;

import com.wardanger.ProjectPorsche.model.Car;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends MongoRepository<Car, String> {

}

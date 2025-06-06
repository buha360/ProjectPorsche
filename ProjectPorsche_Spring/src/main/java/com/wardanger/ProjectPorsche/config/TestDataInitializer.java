package com.wardanger.ProjectPorsche.config;

import com.wardanger.ProjectPorsche.model.Car;
import com.wardanger.ProjectPorsche.model.Customer;
import com.wardanger.ProjectPorsche.repository.CarRepository;
import com.wardanger.ProjectPorsche.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class TestDataInitializer implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;

    @Override
    public void run(String... args) {
        carRepository.deleteAll();
        customerRepository.deleteAll();

        // adatok újra initializálása

        Customer anna = new Customer(null, "anna123", "anna@mail.com", "pass1", "06201234567", "Porsche krt 1", null);
        Customer bela = new Customer(null, "bela456", "bela@mail.com", "pass2", "06301234567", "Benzin út 5", null);
        customerRepository.saveAll(List.of(anna, bela));

        Car car1 = new Car(null, "ABC123", "Toyota", "Corolla", 2010, "petrol", anna);
        Car car2 = new Car(null, "XYZ999", "Nissan", "Almera", 2005, "diesel", anna);
        Car car3 = new Car(null, "MMM333", "BMW", "320i", 2020, "hybrid", bela);
        carRepository.saveAll(List.of(car1, car2, car3));
    }
}
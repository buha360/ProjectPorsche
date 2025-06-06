DROP DATABASE IF EXISTS projectporsche;
CREATE DATABASE projectporsche;
USE projectporsche;

-- Users tábla létrehozása
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    address VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255)
);

-- Cars tábla létrehozása
CREATE TABLE cars (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    model VARCHAR(255) NOT NULL,
    year INT NOT NULL,
    date_added TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Service Records tábla létrehozása
CREATE TABLE service_records (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    car_id BIGINT,
    price DECIMAL(10, 2),
    service_date DATE NOT NULL,
    description TEXT,
    FOREIGN KEY (car_id) REFERENCES cars(id) ON DELETE CASCADE
);

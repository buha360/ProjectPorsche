-- Users adatok
INSERT INTO users (name, phone_number, address, email, password, role)
VALUES 
('John Doe', '123-456-7890', '123 Main St, Springfield', 'johndoe@example.com', '$2b$12$4hQDk.E2FFaerWgJrnWYQeE2E7JjCe6A5H07o8TohrA4xFndh4bpC', 'USER'),
('Jane Smith', '987-654-3210', '456 Elm St, Springfield', 'janesmith@example.com', '$2b$12$4hQDk.E2FFaerWgJrnWYQeE2E7JjCe6A5H07o8TohrA4xFndh4bpC', 'USER'),
('Michael Johnson', '555-123-4567', '789 Oak St, Springfield', 'mjohnson@example.com', '$2b$12$4hQDk.E2FFaerWgJrnWYQeE2E7JjCe6A5H07o8TohrA4xFndh4bpC', 'USER'),
('Emily Davis', '444-555-6666', '101 Pine St, Springfield', 'edavis@example.com', '$2b$12$4hQDk.E2FFaerWgJrnWYQeE2E7JjCe6A5H07o8TohrA4xFndh4bpC', 'USER'),
('William Brown', '333-444-5555', '202 Maple St, Springfield', 'wbrown@example.com', '$2b$12$4hQDk.E2FFaerWgJrnWYQeE2E7JjCe6A5H07o8TohrA4xFndh4bpC', 'USER');

-- Cars adatok
INSERT INTO cars (model, year, date_added, user_id)
VALUES 
('Porsche 911', 2020, NOW(), 1),
('Porsche Cayenne', 2019, NOW(), 2),
('Porsche Taycan', 2021, NOW(), 3),
('Porsche Macan', 2018, NOW(), 4),
('Porsche Panamera', 2022, NOW(), 5);

-- Service Records adatok
INSERT INTO service_records (car_id, price, service_date, description)
VALUES 
(1, 1500.00, '2022-05-01', 'Oil change and filter replacement'),
(1, 1200.00, '2023-01-10', 'Brake pads and rotors replacement'),
(2, 800.00, '2022-06-15', 'Tire replacement'),
(3, 2000.00, '2023-03-20', 'Battery replacement and software update'),
(4, 1300.00, '2021-09-11', 'Transmission fluid change'),
(5, 1800.00, '2022-12-22', 'Full service inspection'),
(3, 1600.00, '2023-07-08', 'Suspension system check and repair'),
(4, 1400.00, '2023-02-18', 'Cooling system repair');
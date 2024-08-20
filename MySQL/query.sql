SELECT u.name AS user_name, c.model AS car_model, c.year AS car_year
FROM users u
JOIN cars c ON u.id = c.user_id;

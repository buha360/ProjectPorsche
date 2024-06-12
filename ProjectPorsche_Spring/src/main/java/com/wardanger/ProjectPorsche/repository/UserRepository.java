package com.wardanger.ProjectPorsche.repository;

import com.wardanger.ProjectPorsche.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}

package com.example.auntificationservice.repository;

import com.example.auntificationservice.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
    boolean existsByUsername(String username);
}

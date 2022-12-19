package com.example.auntificationservice.repository;

import com.example.auntificationservice.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}

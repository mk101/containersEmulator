package com.example.processor.repository;

import com.example.processor.model.Container;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContainerRepository extends CrudRepository<Container, Integer> {
}

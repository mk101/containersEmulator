package com.example.webapi.repository;

import com.example.webapi.model.Container;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContainerRepository extends PagingAndSortingRepository<Container, Integer> {
}

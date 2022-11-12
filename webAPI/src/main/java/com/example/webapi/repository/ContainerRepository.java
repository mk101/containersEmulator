package com.example.webapi.repository;

import com.example.webapi.model.Container;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface ContainerRepository extends PagingAndSortingRepository<Container, Integer> {
    Page<Container> findAllByTimestampBetween(Timestamp start, Timestamp end, Pageable pageable);
}

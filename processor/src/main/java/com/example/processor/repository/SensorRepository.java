package com.example.processor.repository;

import com.example.processor.model.Sensor;
import com.example.processor.model.SensorId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends CrudRepository<Sensor, SensorId> {
}

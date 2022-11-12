package com.example.webapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "sensors")
@IdClass(SensorId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sensor {
    @Id
    private Integer number;

    @Column(name = "sensor_value",nullable = false)
    private Double numeric;

    @Id
    private Integer messageId;
}

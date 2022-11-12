package com.example.webapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "containers")
@NamedEntityGraph(
        name = "full-container",
        attributeNodes = {
                @NamedAttributeNode("sensors")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Container {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer messageId;

    @Column(nullable = false)
    private Integer number;

    @Column(name = "ts", nullable = false)
    private Timestamp timestamp;

    @OneToMany(mappedBy = "messageId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Sensor> sensors;
}
package com.obilet.model;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "journeys")
public class Journey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String from;

    @Column(nullable = false)
    private String to;

    @Column(nullable = false)
    private LocalDateTime departureTime;

    @Column(nullable = false)
    private LocalDateTime arrivalTime;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String transportationType; // BUS, PLANE, TRAIN etc.

    private String companyName;
    private Integer availableSeats;
}

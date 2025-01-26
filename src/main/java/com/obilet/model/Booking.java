package com.obilet.model;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "journey_id", nullable = false)
    private Journey journey;

    @Column(nullable = false)
    private String passengerName;

    @Column(nullable = false)
    private String passengerEmail;

    @Column(nullable = false)
    private String passengerPhone;

    @Column(nullable = false)
    private Integer seatNumber;

    @Column(nullable = false)
    private LocalDateTime bookingTime;

    @Column(nullable = false)
    private String bookingStatus; // CONFIRMED, CANCELLED, PENDING

    private String paymentMethod;
    private Double totalAmount;
}

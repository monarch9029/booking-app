package com.softwaretask.Booking_app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;



    @ManyToOne
    @JoinColumn(name="load_id",nullable=false)
    private Load load;

    private String transporterId;

    private double proposedRate;

    private String comment;

   @Enumerated(EnumType.STRING)
    private BookingStatus status=BookingStatus.PENDING;

    private LocalDateTime requestedAt;
}

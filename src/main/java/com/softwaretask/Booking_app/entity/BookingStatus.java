package com.softwaretask.Booking_app.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public enum BookingStatus {
    PENDING,
    ACCEPTED,
    REJECTED
}

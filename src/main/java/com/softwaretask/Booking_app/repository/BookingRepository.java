package com.softwaretask.Booking_app.repository;

import com.softwaretask.Booking_app.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
    List<Booking> findByLoadId(UUID loadUuid);
    List<Booking> findByTransporterId(String transporterId);
}

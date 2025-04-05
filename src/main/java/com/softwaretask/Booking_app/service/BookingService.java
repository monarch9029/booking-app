package com.softwaretask.Booking_app.service;

import com.softwaretask.Booking_app.entity.Booking;
import com.softwaretask.Booking_app.entity.Load;
import com.softwaretask.Booking_app.entity.LoadStatus;
import com.softwaretask.Booking_app.repository.BookingRepository;
import com.softwaretask.Booking_app.repository.LoadRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final LoadRepository loadRepository;
    public BookingService(BookingRepository bookingRepository, LoadRepository loadRepository) {
        this.bookingRepository = bookingRepository;
        this.loadRepository = loadRepository;
    }
    // Create booking
    public Booking createBooking(Booking booking) {
        UUID loadId = booking.getLoad().getId();
        if(loadRepository.findById(loadId).isEmpty()){
            throw new RuntimeException("Load not found");
        }
        Load load = loadRepository.findById(loadId).get();
        if(load.getStatus()== LoadStatus.CANCELLED){
            throw new IllegalStateException("Load status is CANCELLED");
        }
        booking.setLoad(load);
        booking.setRequestedAt(LocalDateTime.now());
        return bookingRepository.save(booking);
    }

    //delete booking
    public void deleteBooking(UUID bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(()->
                new RuntimeException("Booking not found"));
        Load load = booking.getLoad();
        load.setStatus(LoadStatus.CANCELLED);
        loadRepository.save(load);
        bookingRepository.deleteById(bookingId);
    }

    // Get booking by ID
    public Booking getBooking( UUID bookingId) {
        return bookingRepository.findById(bookingId).orElseThrow(()->
                new RuntimeException("Booking not found"));
    }

    // Update booking
    public Booking updateBooking(UUID bookingId, Booking booking) {
        Booking existingBooking = bookingRepository.findById(bookingId).orElseThrow(()->
                new RuntimeException("Booking not found"));
       Load load = loadRepository.findById(existingBooking.getLoad().getId()).orElseThrow(()->
                new RuntimeException("Load not found"));
        if(load.getStatus()== LoadStatus.CANCELLED){
            throw new IllegalStateException("Load status is CANCELLED");
        }
        existingBooking.setComment(booking.getComment());
        existingBooking.setTransporterId(booking.getTransporterId());
        existingBooking.setLoad(load);
        existingBooking.setProposedRate(booking.getProposedRate());
        existingBooking.setRequestedAt(LocalDateTime.now());
        return bookingRepository.save(existingBooking);
    }
}

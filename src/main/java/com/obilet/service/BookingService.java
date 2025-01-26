package com.obilet.service;

import com.obilet.model.Booking;
import com.obilet.model.Journey;
import com.obilet.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final JourneyService journeyService;

    public Booking createBooking(Long journeyId, Booking booking) {
        Journey journey = journeyService.getJourneyById(journeyId);
        
        if (journey.getAvailableSeats() <= 0) {
            throw new RuntimeException("No available seats for this journey");
        }

        booking.setJourney(journey);
        booking.setBookingTime(LocalDateTime.now());
        booking.setBookingStatus("CONFIRMED");
        booking.setTotalAmount(journey.getPrice());

        // Update available seats
        journey.setAvailableSeats(journey.getAvailableSeats() - 1);
        journeyService.updateJourney(journeyId, journey);

        return bookingRepository.save(booking);
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
    }

    public List<Booking> getBookingsByEmail(String email) {
        return bookingRepository.findByPassengerEmail(email);
    }

    public void cancelBooking(Long id) {
        Booking booking = getBookingById(id);
        booking.setBookingStatus("CANCELLED");
        
        // Return the seat to available seats
        Journey journey = booking.getJourney();
        journey.setAvailableSeats(journey.getAvailableSeats() + 1);
        journeyService.updateJourney(journey.getId(), journey);

        bookingRepository.save(booking);
    }
}

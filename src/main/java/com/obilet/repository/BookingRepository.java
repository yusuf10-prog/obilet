package com.obilet.repository;

import com.obilet.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByPassengerEmail(String email);
    
    List<Booking> findByJourneyId(Long journeyId);
    
    List<Booking> findByBookingStatus(String status);
}

package com.obilet.repository;

import com.obilet.model.Journey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JourneyRepository extends JpaRepository<Journey, Long> {
    List<Journey> findByFromAndToAndDepartureTimeBetween(
        String from, String to, LocalDateTime startTime, LocalDateTime endTime);
    
    List<Journey> findByTransportationType(String transportationType);
    
    List<Journey> findByCompanyName(String companyName);
}

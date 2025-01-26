package com.obilet.service;

import com.obilet.model.Journey;
import com.obilet.repository.JourneyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JourneyService {
    private final JourneyRepository journeyRepository;

    public List<Journey> searchJourneys(String from, String to, LocalDateTime date) {
        LocalDateTime endOfDay = date.plusDays(1).minusSeconds(1);
        return journeyRepository.findByFromAndToAndDepartureTimeBetween(from, to, date, endOfDay);
    }

    public Journey getJourneyById(Long id) {
        return journeyRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Journey not found with id: " + id));
    }

    public List<Journey> getJourneysByTransportationType(String type) {
        return journeyRepository.findByTransportationType(type);
    }

    public Journey createJourney(Journey journey) {
        return journeyRepository.save(journey);
    }

    public void updateJourney(Long id, Journey journey) {
        if (!journeyRepository.existsById(id)) {
            throw new RuntimeException("Journey not found with id: " + id);
        }
        journey.setId(id);
        journeyRepository.save(journey);
    }

    public void deleteJourney(Long id) {
        journeyRepository.deleteById(id);
    }
}

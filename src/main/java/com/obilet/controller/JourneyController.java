package com.obilet.controller;

import com.obilet.model.Journey;
import com.obilet.service.JourneyService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/journeys")
@RequiredArgsConstructor
public class JourneyController {
    private final JourneyService journeyService;

    @GetMapping("/search")
    public ResponseEntity<List<Journey>> searchJourneys(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return ResponseEntity.ok(journeyService.searchJourneys(from, to, date));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Journey> getJourney(@PathVariable Long id) {
        return ResponseEntity.ok(journeyService.getJourneyById(id));
    }

    @GetMapping("/type/{transportationType}")
    public ResponseEntity<List<Journey>> getJourneysByType(@PathVariable String transportationType) {
        return ResponseEntity.ok(journeyService.getJourneysByTransportationType(transportationType));
    }

    @PostMapping
    public ResponseEntity<Journey> createJourney(@RequestBody Journey journey) {
        return ResponseEntity.ok(journeyService.createJourney(journey));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateJourney(@PathVariable Long id, @RequestBody Journey journey) {
        journeyService.updateJourney(id, journey);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJourney(@PathVariable Long id) {
        journeyService.deleteJourney(id);
        return ResponseEntity.ok().build();
    }
}

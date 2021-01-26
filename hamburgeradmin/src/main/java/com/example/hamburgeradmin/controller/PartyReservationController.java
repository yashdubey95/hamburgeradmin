package com.example.hamburgeradmin.controller;

import com.example.hamburgeradmin.model.PartyReservation;
import com.example.hamburgeradmin.services.PartyReservationServices;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Log4j2
public class PartyReservationController {

    @Autowired
    PartyReservationServices partyReservationServices;

    @GetMapping("/reservations")
    public ResponseEntity<List<PartyReservation>> getAllReservations(@RequestParam(required = false) String name) {
        return partyReservationServices.getAllReservations(name);
    }

    @GetMapping("/reservations/{id}")
    public ResponseEntity<PartyReservation> getReservationById(@PathVariable("id") String id) {
        return partyReservationServices.getReservationById(id);
    }

    @PostMapping("/reservations")
    public ResponseEntity<PartyReservation> createReservation(@RequestBody PartyReservation reservation) {
        return partyReservationServices.createReservation(reservation);
    }

    @PutMapping("/reservations/{id}")
    public ResponseEntity<PartyReservation> updateReservation(@PathVariable("id") String id, @RequestBody PartyReservation reservation) {
        return partyReservationServices.updateReservation(id, reservation);
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<HttpStatus> deleteReservation(@PathVariable("id") String id) {
        return partyReservationServices.deleteReservation(id);
    }

    @DeleteMapping("/reservations")
    public ResponseEntity<HttpStatus> deleteReservations() {
        return partyReservationServices.deleteReservations();
    }

    @GetMapping("/reservations/partyType/{partyType}")
    public ResponseEntity<List<PartyReservation>> getByPartyType(@PathVariable("partyType") String partyType) {
        return partyReservationServices.getByPartyType(partyType);
    }
}

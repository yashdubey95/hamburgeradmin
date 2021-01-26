package com.example.hamburgeradmin.services;

import com.example.hamburgeradmin.model.PartyReservation;
import com.example.hamburgeradmin.repository.PartyReservationRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class PartyReservationServices {

    @Autowired
    PartyReservationRepository partyReservationRepository;

    public ResponseEntity<List<PartyReservation>> getAllReservations(String name) {
        try {
            List<PartyReservation> reservation = new ArrayList<>();

            if (name == null)
                partyReservationRepository.findAll().forEach(reservation::add);
            else
                partyReservationRepository.findByCustomerName(name).forEach(reservation::add);

            if (reservation.isEmpty()) {
                log.info("No Reservation found!");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(reservation, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<PartyReservation> getReservationById(String id) {
        Optional<PartyReservation> reservationData = partyReservationRepository.findById(id);

        if (reservationData.isPresent()) {
            return new ResponseEntity<>(reservationData.get(), HttpStatus.OK);
        } else {
            log.info("Reservation with id: "+id+" not found!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<PartyReservation> createReservation(PartyReservation reservation) {
        try {
            PartyReservation _reservation = partyReservationRepository.save(reservation);
            log.info("New Reservation created!");
            return new ResponseEntity<>(_reservation, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<PartyReservation> updateReservation(@PathVariable("id") String id, @RequestBody PartyReservation reservation) {
        Optional<PartyReservation> reservationData = partyReservationRepository.findByReservationId(id);

        if (reservationData.isPresent()) {
            PartyReservation _reservation = reservationData.get();
            _reservation.setCustomerName(reservation.getCustomerName());
            _reservation.setPeopleCount(reservation.getPeopleCount());
            _reservation.setTable(reservation.getTable());
            _reservation.setTime(reservation.getTime());
            _reservation.setPartyType(reservation.getPartyType());
            _reservation.setDate(reservation.getDate());
            return new ResponseEntity<>(partyReservationRepository.save(_reservation), HttpStatus.OK);
        } else {
            log.info("Reservation with id: "+id+" not found!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deleteReservation(String id) {
        try {
            partyReservationRepository.deleteById(id);
            log.info("Reservation with id: "+id+" not found!");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<HttpStatus> deleteReservations() {
        try {
            partyReservationRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<PartyReservation>> getByPartyType(String partyType) {
        try {
            List<PartyReservation> partyReservations = new ArrayList<>();

            partyReservationRepository.findByPartyType(partyType).forEach(partyReservations::add);

            if (partyReservations.isEmpty()) {
                log.info("Menu with combo not found!");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(partyReservations, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

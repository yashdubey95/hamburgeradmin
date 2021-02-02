package com.example.hamburgeradmin.services;

import com.example.hamburgeradmin.assemblers.PartyReservationAssembler;
import com.example.hamburgeradmin.dto.PartyReservationDTO;
import com.example.hamburgeradmin.exception.InternalServerErrorException;
import com.example.hamburgeradmin.exception.ResourceNotFoundException;
import com.example.hamburgeradmin.model.PartyReservation;
import com.example.hamburgeradmin.repository.PartyReservationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

/**
 * @author Yash Dubey
 * <p>
 * This class implements methods to fetch, store, update and delete records from PartyReservation entity
 */
@AllArgsConstructor
@Service
@Log4j2
public class PartyReservationServices {

    private final PartyReservationRepository partyReservationRepository;
    private final PagedResourcesAssembler pagedResourcesAssembler;
    private final PartyReservationAssembler partyReservationAssembler;

    public CollectionModel<PartyReservationDTO> getAllReservations(String name, String partyType, int page, int size) {
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<PartyReservation> pageReservations;

            if (name == null && partyType == null)
                pageReservations = partyReservationRepository.findAll(paging);
            else if(partyType == null)
                pageReservations = partyReservationRepository.findByCustomerNameContaining(name, paging);
            else
                pageReservations = partyReservationRepository.findByPartyType(partyType, paging);

            if(! CollectionUtils.isEmpty(pageReservations.getContent())) return pagedResourcesAssembler.toModel(pageReservations, partyReservationAssembler);
            return null;

        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public PartyReservationDTO getReservationById(String id) {
        Optional<PartyReservation> reservationData = partyReservationRepository.findById(id);
        PartyReservation reservation;
        if (reservationData.isPresent()) {
            reservation = reservationData.get();
            return partyReservationAssembler.toModel(reservation);
        }
        return null;
    }

    public PartyReservationDTO createReservation(PartyReservation reservation) {
        try {
            PartyReservation createReservation = partyReservationRepository.save(reservation);
            return partyReservationAssembler.toModel(createReservation);
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public PartyReservationDTO updateReservation(@PathVariable("id") String id, @RequestBody PartyReservation reservation) {
        Optional<PartyReservation> reservationData = partyReservationRepository.findByReservationId(id);
        PartyReservation reservationDTO;
        if (reservationData.isPresent()) {
            PartyReservation updateReservation = reservationData.get();
            updateReservation.setCustomerName(reservation.getCustomerName());
            updateReservation.setPeopleCount(reservation.getPeopleCount());
            updateReservation.setTable(reservation.getTable());
            updateReservation.setStartTime(reservation.getStartTime());
            updateReservation.setEndTime(reservation.getEndTime());
            updateReservation.setPartyType(reservation.getPartyType());
            updateReservation.setDate(reservation.getDate());
            reservationDTO = partyReservationRepository.save(updateReservation);
            return partyReservationAssembler.toModel(reservationDTO);
        }
        return null;
    }

    public String deleteReservation(String id) {
        if (partyReservationRepository.existsById(id)){
            partyReservationRepository.deleteById(id);
            return "Reservation with id: "+id+" deleted";
        }
        throw new ResourceNotFoundException("Reservation", id);
    }

    public String deleteReservations() {
        partyReservationRepository.deleteAll();
        return "All the Menu Items are deleted";
    }
}

package com.example.hamburgeradmin.assemblers;

import com.example.hamburgeradmin.dto.PartyReservationDTO;
import com.example.hamburgeradmin.model.PartyReservation;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

/**
 * @author Yash Dubey
 *
 * This class encapsulates data transfer object for PartyReservation entity
 */
@AllArgsConstructor
@Component
public class PartyReservationAssembler implements RepresentationModelAssembler<PartyReservation, PartyReservationDTO> {

    public PartyReservationDTO toModel(PartyReservation entity) {
        return new PartyReservationDTO(entity.getCustomerName(), entity.getPartyType(), entity.getPeopleCount(), entity.getTable(), entity.getDate(), entity.getStartTime(), entity.getEndTime());
    }
}

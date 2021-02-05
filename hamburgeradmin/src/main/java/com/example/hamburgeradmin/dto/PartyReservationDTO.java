package com.example.hamburgeradmin.dto;

import com.example.hamburgeradmin.model.PartyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDate;
import java.time.LocalTime;

@Relation(collectionRelation = "partyReservations")
@Data
@AllArgsConstructor
public class PartyReservationDTO extends RepresentationModel<PartyReservationDTO> {
    private String customerName;
    private String locationName;
    private PartyType partyType;
    private Integer peopleCount;
    private String table;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
}

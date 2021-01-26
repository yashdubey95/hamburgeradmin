package com.example.hamburgeradmin.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "party_reservations")
@Data
public class PartyReservation {
    @Id
    private String reservationId;
    private String customerName;
    private String partyType;
    private int peopleCount;
    private String table;
    private String date;
    private String time;
}

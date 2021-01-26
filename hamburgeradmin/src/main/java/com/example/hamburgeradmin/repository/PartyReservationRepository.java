package com.example.hamburgeradmin.repository;

import com.example.hamburgeradmin.model.PartyReservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartyReservationRepository extends MongoRepository<PartyReservation, String>  {
    List<PartyReservation> findByCustomerName(String name);
    Optional<PartyReservation> findByReservationId(String id);
    List<PartyReservation> findByPartyType(String partyType);
}

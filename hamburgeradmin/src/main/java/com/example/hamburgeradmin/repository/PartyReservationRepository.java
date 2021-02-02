package com.example.hamburgeradmin.repository;

import com.example.hamburgeradmin.model.PartyReservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartyReservationRepository extends MongoRepository<PartyReservation, String>  {
    Page<PartyReservation> findByCustomerNameContaining(String name, Pageable pageable);
    Optional<PartyReservation> findByReservationId(String id);
    Page<PartyReservation> findByPartyType(String partyType, Pageable pageable);
}

package com.example.hamburgeradmin.repository;

import com.example.hamburgeradmin.model.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends MongoRepository<Location, String> {
    Page<Location> findByNameContaining(String name, Pageable pageable);
    Optional<Location> findByLocationId(String id);
    Page<Location> findByActive(Boolean active, Pageable pageable);
}

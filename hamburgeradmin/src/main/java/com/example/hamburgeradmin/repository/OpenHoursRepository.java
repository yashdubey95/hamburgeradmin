package com.example.hamburgeradmin.repository;

import com.example.hamburgeradmin.model.OpenHours;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OpenHoursRepository extends MongoRepository<OpenHours, String> {
    List<OpenHours> findByDayContaining(String day);
    Optional<OpenHours> findByOpenHoursId(String id);
}

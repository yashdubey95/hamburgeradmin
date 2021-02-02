package com.example.hamburgeradmin.repository;

import com.example.hamburgeradmin.model.APIDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface APIDetailsRepository extends JpaRepository<APIDetails, Integer> {
    Page<APIDetails> findByReqName(String reqName, Pageable pageable);
    Page<APIDetails> findByReqTimeStamp(LocalDate reqTimeStamp, Pageable pageable);
    @Query("SELECT a FROM APIDetails a WHERE a.reqName = :reqName AND a.reqTimeStamp = :reqTimeStamp")
    Page<APIDetails> findByReqNameAndReqTimeStamp(String reqName,LocalDate reqTimeStamp, Pageable pageable);
}

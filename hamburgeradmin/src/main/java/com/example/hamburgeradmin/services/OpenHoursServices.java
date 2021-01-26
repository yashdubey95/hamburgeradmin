package com.example.hamburgeradmin.services;

import com.example.hamburgeradmin.model.OpenHours;
import com.example.hamburgeradmin.repository.OpenHoursRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class OpenHoursServices {
    @Autowired
    OpenHoursRepository openHoursRepository;

    public ResponseEntity<List<OpenHours>> getAllOpenHours(String name) {
        try {
            List<OpenHours> location = new ArrayList<>();

            if (name == null)
                openHoursRepository.findAll().forEach(location::add);
            else
                openHoursRepository.findByDayContaining(name).forEach(location::add);

            if (location.isEmpty()) {
                log.info("Parameter Name : {}",name);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(location, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<OpenHours> getOpenHoursById(String id) {
        Optional<OpenHours> locationData = openHoursRepository.findById(id);

        if (locationData.isPresent()) {
            return new ResponseEntity<>(locationData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<OpenHours> createOpenHours(OpenHours openHours) {
        try {
            OpenHours _openHours = openHoursRepository.save(openHours);
            return new ResponseEntity<>(_openHours, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<OpenHours> updateOpenHours(String id, OpenHours openHours) {
        Optional<OpenHours> openHoursData = openHoursRepository.findByOpenHoursId(id);

        if (openHoursData.isPresent()) {
            OpenHours _openHours = openHoursData.get();
            _openHours.setDay(openHours.getDay());
            _openHours.setOpenTime(openHours.getOpenTime());
            _openHours.setCloseTime(openHours.getCloseTime());
            return new ResponseEntity<>(openHoursRepository.save(_openHours), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deleteOpenHours(String id) {
        try {
            openHoursRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<HttpStatus> deleteAllOpenHours() {
        try {
            openHoursRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

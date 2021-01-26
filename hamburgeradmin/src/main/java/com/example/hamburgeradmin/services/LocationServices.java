package com.example.hamburgeradmin.services;

import com.example.hamburgeradmin.model.Location;
import com.example.hamburgeradmin.repository.LocationRepository;
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
public class LocationServices {

    @Autowired
    LocationRepository locationRepository;

    public ResponseEntity<List<Location>> getAllLocations(String name){
        try {
            List<Location> location = new ArrayList<>();

            if (name == null)
                locationRepository.findAll().forEach(location::add);
            else
                locationRepository.findByNameContaining(name).forEach(location::add);

            if (location.isEmpty()) {
                log.info("Parameter Name : {}",name);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(location, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Location> getLocationById(String id) {
        Optional<Location> locationData = locationRepository.findById(id);

        if (locationData.isPresent()) {
            return new ResponseEntity<>(locationData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Location> createLocation(Location location) {
        try {
            System.out.println(location.getActive());
            Location _location = locationRepository.save(location);
            return new ResponseEntity<>(_location, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Location> updateLocation(String id, Location location){
        Optional<Location> locationData = locationRepository.findByLocationId(id);

        if (locationData.isPresent()) {
            Location _location = locationData.get();
            _location.setName(location.getName());
            _location.setLatitude(location.getLatitude());
            _location.setLongitude(location.getLongitude());
            _location.setPhone(location.getPhone());
            _location.setAddress(location.getAddress());
            _location.setActive(location.getActive());
            return new ResponseEntity<>(locationRepository.save(_location), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deleteLocation(String id) {
        try {
            locationRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<HttpStatus> deleteAllLocations() {
        try {
            locationRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Location>> getByActive(Boolean active) {
        try {
            List<Location> location = new ArrayList<>();

            locationRepository.findByActive(active).forEach(location::add);

            if (location.isEmpty()) {
                log.info("Menu with combo not found!");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(location, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.example.hamburgeradmin.controller;

import com.example.hamburgeradmin.model.Location;
import com.example.hamburgeradmin.services.LocationServices;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Log4j2
public class LocationController {
    @Autowired
    LocationServices locationService;

    @GetMapping("/locations")
    public ResponseEntity<List<Location>> getAllLocations(@RequestParam(required = false) String name) {
        return locationService.getAllLocations(name);
    }

    @GetMapping("/locations/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable("id") String id) {
        return locationService.getLocationById(id);
    }

    @PostMapping("/locations")
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
        return locationService.createLocation(location);
    }

    @PutMapping("/locations/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable("id") String id, @RequestBody Location location) {
        return locationService.updateLocation(id, location);
    }

    @DeleteMapping("/locations/{id}")
    public ResponseEntity<HttpStatus> deleteLocation(@PathVariable("id") String id) {
        return locationService.deleteLocation(id);
    }

    @DeleteMapping("/locations")
    public ResponseEntity<HttpStatus> deleteAllLocations() {
        return locationService.deleteAllLocations();
    }

    @GetMapping("/locations/active/{active}")
    public ResponseEntity<List<Location>> getByActive(@PathVariable("active") Boolean active) {
        return locationService.getByActive(active);
    }
}


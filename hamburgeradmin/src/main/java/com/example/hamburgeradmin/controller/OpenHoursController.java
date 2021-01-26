package com.example.hamburgeradmin.controller;

import com.example.hamburgeradmin.model.OpenHours;
import com.example.hamburgeradmin.services.OpenHoursServices;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Log4j2
public class OpenHoursController {
    @Autowired
    OpenHoursServices openHoursServices;

    @GetMapping("/open-hours")
    public ResponseEntity<List<OpenHours>> getAllOpenHours(@RequestParam(required = false) String name) {
        return openHoursServices.getAllOpenHours(name);
    }

    @GetMapping("/open-hours/{id}")
    public ResponseEntity<OpenHours> getOpenHoursById(@PathVariable("id") String id) {
        return openHoursServices.getOpenHoursById(id);
    }

    @PostMapping("/open-hours")
    public ResponseEntity<OpenHours> createOpenHours(@RequestBody OpenHours openHours) {
        return openHoursServices.createOpenHours(openHours);
    }

    @PutMapping("/open-hours/{id}")
    public ResponseEntity<OpenHours> updateOpenHours(@PathVariable("id") String id, @RequestBody OpenHours openHours) {
        return openHoursServices.updateOpenHours(id, openHours);
    }

    @DeleteMapping("/open-hours/{id}")
    public ResponseEntity<HttpStatus> deleteOpenHours(@PathVariable("id") String id) {
        return openHoursServices.deleteOpenHours(id);
    }

    @DeleteMapping("/open-hours")
    public ResponseEntity<HttpStatus> deleteAllOpenHours() {
        return openHoursServices.deleteAllOpenHours();
    }
}


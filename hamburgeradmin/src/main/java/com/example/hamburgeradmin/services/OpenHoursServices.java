package com.example.hamburgeradmin.services;

import com.example.hamburgeradmin.assemblers.LocationAssembler;
import com.example.hamburgeradmin.dto.LocationDTO;
import com.example.hamburgeradmin.exception.ResourceNotFoundException;
import com.example.hamburgeradmin.model.Location;
import com.example.hamburgeradmin.repository.LocationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Yash Dubey
 * <p>
 * This class implements methods to update and delete Open Hours records from Location entity
 */
@AllArgsConstructor
@Service
@Log4j2
public class OpenHoursServices {

    private final LocationRepository locationRepository;
    private final LocationAssembler locationAssembler;

    public LocationDTO updateOpenHours(String id, Location openHours) {
        log.info("Entering updateOpenHours method with id = {} and openHours body = {}", id, openHours.getOpenHours());
        Optional<Location> openHoursData = locationRepository.findByLocationId(id);
        Location hoursDTO;
        if (openHoursData.isPresent()) {
            Location updatedHours = openHoursData.get();
            updatedHours.setOpenHours(openHours.getOpenHours());
            hoursDTO = locationRepository.save(updatedHours);
            log.info("Open Hours updated with Location id: {}", id);
            log.info("Finishing updateOpenHours method");
            return locationAssembler.toModel(hoursDTO);
        }
        log.error("Location with id: "+id+" does not exists");
        throw new ResourceNotFoundException("Location with id: "+id+" does not exists");
    }
}
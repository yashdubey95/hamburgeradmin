package com.example.hamburgeradmin.services;

import com.example.hamburgeradmin.assemblers.LocationAssembler;
import com.example.hamburgeradmin.dto.LocationDTO;
import com.example.hamburgeradmin.exception.BadRequestException;
import com.example.hamburgeradmin.exception.InternalServerErrorException;
import com.example.hamburgeradmin.exception.ResourceNotFoundException;
import com.example.hamburgeradmin.model.Location;
import com.example.hamburgeradmin.repository.LocationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author Yash Dubey
 * <p>
 * This class implements methods to fetch, store, update and delete records from Location entity
 */
@AllArgsConstructor
@Service
@Log4j2
public class LocationServices {

    private final LocationAssembler locationAssembler;
    private final LocationRepository locationRepository;
    private final PagedResourcesAssembler pagedResourcesAssembler;

    public CollectionModel<LocationDTO> getAllLocations(String name, int page, int size){
        log.info("Entering getAllLocations method with name = {}", name);
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Location> pageLocations;
            if (name == null)
                pageLocations = locationRepository.findAll(paging);
            else
                pageLocations = locationRepository.findByNameContaining(name, paging);

            log.info("Finishing getAllLocations method");
            if(! CollectionUtils.isEmpty(pageLocations.getContent())) return pagedResourcesAssembler.toModel(pageLocations, locationAssembler);
            return null;
        } catch (Exception e) {
            log.error("Internal Server Error - getAllLocations method");
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public LocationDTO getLocationById(String id) {
        log.info("Entering getLocationById method with id = {}", id);
        Optional<Location> locationData = locationRepository.findById(id);
        Location location;
        log.info("Finishing getLocationById method");
        if (locationData.isPresent()) {
            location = locationData.get();
            return locationAssembler.toModel(location);
        }
        return null;
    }

    public LocationDTO createLocation(Location location) {
        log.info("Entering createLocation method with location body = {}", location);
        try {
            Location createdlocation = locationRepository.save(location);
            log.info("Location created with Location id: {}", createdlocation.getLocationId());
            log.info("Finishing createLocation method");
            return locationAssembler.toModel(createdlocation);
        } catch (Exception e) {
            log.error("Bad Request Error - createLocation method");
            throw new BadRequestException("One or more fields in location is/are Invalid");
        }
    }

    public LocationDTO updateLocation(String id, Location location){
        log.info("Entering updateLocation method with id = {} and location body = {}", id, location);
        Optional<Location> locationData = locationRepository.findByLocationId(id);
        Location locationDTO;
        if (locationData.isPresent()) {
            Location updatedLocation = locationData.get();
            updatedLocation.setName(location.getName());
            updatedLocation.setLatitude(location.getLatitude());
            updatedLocation.setLongitude(location.getLongitude());
            updatedLocation.setPhone(location.getPhone());
            updatedLocation.setAddress(location.getAddress());
            updatedLocation.setActive(location.getActive());
            locationDTO = locationRepository.save(updatedLocation);
            log.info("Location updated with Location id: {}", id);
            log.info("Finishing updateLocation method");
            return locationAssembler.toModel(locationDTO);
        }
        log.error("Location with id: "+id+" does not exists");
        throw new ResourceNotFoundException("Location with id: "+id+" does not exists");
    }

    public String deleteLocation(String id) {
        log.info("Entering deleteLocation method with id = {}", id);
        if (locationRepository.existsById(id)){
            locationRepository.deleteById(id);
            log.info("Location deleted with Location id: {}", id);
            log.info("Finishing updateLocation method");
            return "Location with id: "+id+" deleted";
        }
        log.error("Location with id: "+id+" does not exists");
        throw new ResourceNotFoundException("Location with id: "+id+" does not exists");
    }

    public String deleteAllLocations() {
        log.info("Entering deleteAllLocations method");
        try{
            locationRepository.deleteAll();
            log.info("All Locations deleted");
            log.info("Finishing deleteAllLocations method");
            return "All Location deleted";
        } catch (InternalServerErrorException e) {
            log.error("Internal Server Error - deleteAllLocations method");
            throw new InternalServerErrorException("Internal Server Error");
        }

    }

    public CollectionModel<LocationDTO> getByActive(Boolean active, int page, int size) {
        log.info("Entering getByActive method with active = {}", active);
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Location> pageLocations = locationRepository.findByActive(true, paging);
            log.info("Finishing getByActive method");
            if(! CollectionUtils.isEmpty(pageLocations.getContent())) return pagedResourcesAssembler.toModel(pageLocations, locationAssembler);
            return null;
        } catch (BadRequestException e) {
            log.error("Bad Request Error - getByActive method");
            throw new BadRequestException("Active should be a Boolean");
        }
    }
}
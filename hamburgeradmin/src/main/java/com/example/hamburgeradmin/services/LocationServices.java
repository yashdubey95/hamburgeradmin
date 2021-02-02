package com.example.hamburgeradmin.services;

import com.example.hamburgeradmin.assemblers.LocationAssembler;
import com.example.hamburgeradmin.dto.LocationDTO;
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
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Location> pageLocations;
            if (name == null)
                pageLocations = locationRepository.findAll(paging);
            else
                pageLocations = locationRepository.findByNameContaining(name, paging);

            if(! CollectionUtils.isEmpty(pageLocations.getContent())) return pagedResourcesAssembler.toModel(pageLocations, locationAssembler);
            return null;

        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public LocationDTO getLocationById(String id) {
        Optional<Location> locationData = locationRepository.findById(id);
        Location location;
        if (locationData.isPresent()) {
            location = locationData.get();
            return locationAssembler.toModel(location);
        }
        return null;
    }

    public LocationDTO createLocation(Location location) {
        try {
            Location createdlocation = locationRepository.save(location);
            return locationAssembler.toModel(createdlocation);
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public LocationDTO updateLocation(String id, Location location){
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
            return locationAssembler.toModel(locationDTO);
        }
        return null;
    }

    public String deleteLocation(String id) {
        if (locationRepository.existsById(id)){
            locationRepository.deleteById(id);
            return "Location with id: "+id+" deleted";
        }
        throw new ResourceNotFoundException("Location", id);
    }

    public String deleteAllLocations() {
        locationRepository.deleteAll();
        return "All Location deleted";
    }

    public CollectionModel<LocationDTO> getByActive(Boolean active, int page, int size) {
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Location> pageLocations = locationRepository.findByActive(true, paging);

            if(! CollectionUtils.isEmpty(pageLocations.getContent())) return pagedResourcesAssembler.toModel(pageLocations, locationAssembler);
            return null;
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }
}

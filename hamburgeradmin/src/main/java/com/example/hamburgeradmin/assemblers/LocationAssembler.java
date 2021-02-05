package com.example.hamburgeradmin.assemblers;

import com.example.hamburgeradmin.dto.LocationDTO;
import com.example.hamburgeradmin.model.Location;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

/**
 * @author Yash Dubey
 *
 * This class encapsulates data transfer object for Location entity
 */
@AllArgsConstructor
@Component
public class LocationAssembler implements RepresentationModelAssembler<Location, LocationDTO> {

    public LocationDTO toModel(Location entity) {
        return new LocationDTO(entity.getName(), entity.getLatitude(), entity.getLongitude(), entity.getAddress(), entity.getPhone(), entity.getActive(), entity.getOpenHours());
    }
}


package com.example.hamburgeradmin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

@Relation(collectionRelation = "locations")
@Data
@AllArgsConstructor
public class LocationDTO extends RepresentationModel<LocationDTO> {
    private String name;
    private Double latitude;
    private Double longitude;
    private String address;
    private String phone;
    private Boolean active;
    private List openHours;
}

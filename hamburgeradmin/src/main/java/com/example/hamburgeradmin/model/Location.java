package com.example.hamburgeradmin.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "locations")
@Data
public class Location {
    @Id
    private String locationId;
    private String name;
    private Double latitude;
    private Double longitude;
    private String address;
    private String phone;
    private Boolean active;
}

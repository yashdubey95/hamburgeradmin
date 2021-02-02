package com.example.hamburgeradmin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
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
    private List<OpenHours> openHours;
}

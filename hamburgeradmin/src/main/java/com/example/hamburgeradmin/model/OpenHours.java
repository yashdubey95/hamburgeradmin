package com.example.hamburgeradmin.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "open_hours")
@Data
public class OpenHours {
    @Id
    private String openHoursId;
    private String day;
    private String openTime;
    private String closeTime;
}

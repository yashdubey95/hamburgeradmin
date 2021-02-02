package com.example.hamburgeradmin.dto;

import com.example.hamburgeradmin.model.DayOfWeek;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalTime;

@Relation(collectionRelation = "hours")
@Data
@AllArgsConstructor
public class OpenHoursDTO extends RepresentationModel<OpenHoursDTO> {
    private DayOfWeek day;
    private LocalTime openTime;
    private LocalTime closeTime;
}

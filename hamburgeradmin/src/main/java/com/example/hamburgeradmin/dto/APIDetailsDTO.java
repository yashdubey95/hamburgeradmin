package com.example.hamburgeradmin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDate;

@Relation(collectionRelation = "apidetails")
@Data
@AllArgsConstructor
public class APIDetailsDTO extends RepresentationModel<APIDetailsDTO> {
    private String reqName;
    private String reqUrl;
    private LocalDate reqTimeStamp;
    private Long reqExecTime;
}

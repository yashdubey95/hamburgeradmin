package com.example.hamburgeradmin.controller;

import com.example.hamburgeradmin.dto.APIDetailsDTO;
import com.example.hamburgeradmin.model.Location;
import com.example.hamburgeradmin.services.APIDetailsServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * @author Yash Dubey
 * <p>
 * This class exposes resources to interact with APIDetails entity
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api")
@Log4j2
@Tag(name = "APIDetails Controller", description = "Resources related to APIDetails entity")
public class APIDetailsController {

    private final APIDetailsServices apiDetailsServices;

    @Operation(summary = "Get all API Execution Details in the APIDetails Entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "API Execution Detials found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Location.class)) }),
            @ApiResponse(responseCode = "204", description = "No content",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @GetMapping("/exectime")
    public ResponseEntity getAllExecTime(
            @RequestParam(required = false) String reqName,
            @RequestParam(required = false) LocalDate reqTimeStamp,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        CollectionModel<APIDetailsDTO> apiDetails = apiDetailsServices.getAllExecTime(reqName, reqTimeStamp, page, size);
        if(apiDetails != null) {
            return ResponseEntity.ok(apiDetails);
        }
        return ResponseEntity.noContent().build();
    }
}


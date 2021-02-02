package com.example.hamburgeradmin.controller;

import com.example.hamburgeradmin.dto.LocationDTO;
import com.example.hamburgeradmin.model.Location;
import com.example.hamburgeradmin.model.OpenHours;
import com.example.hamburgeradmin.services.OpenHoursServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yash Dubey
 * <p>
 * This class exposes resources to interact with Open Hours in Location entity
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api")
@Log4j2
@Tag(name = "OpenHours Controller", description = "Resources related to Open Hours in the Location entity")
public class OpenHoursController {
    private final OpenHoursServices openHoursServices;

    @Operation(summary = "Update Open Hours for a Location by id in the Location Entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Open Hours for Location updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OpenHours.class)) }),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content) })
    @PutMapping("/hours/{id}")
    public ResponseEntity updateOpenHours(@PathVariable("id") String id, @RequestBody Location openHours) {
        LocationDTO updatedHours = openHoursServices.updateOpenHours(id, openHours);
        if(updatedHours != null) {
            return ResponseEntity.ok(updatedHours);
        }
        return ResponseEntity.notFound().build();
    }
}

package com.example.hamburgeradmin.controller;

import com.example.hamburgeradmin.dto.LocationDTO;
import com.example.hamburgeradmin.model.Location;
import com.example.hamburgeradmin.services.LocationServices;
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

/**
 * @author Yash Dubey
 * <p>
 * This class exposes resources to interact with Location entity
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api")
@Log4j2
@Tag(name = "Location Controller", description = "Resources related to Location entity")
public class LocationController {
    private final LocationServices locationService;

    @Operation(summary = "Get all locations in the Location Entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Locations found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Location.class)) }),
            @ApiResponse(responseCode = "204", description = "No content",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @GetMapping("/locations")
    public ResponseEntity getAllLocations(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        CollectionModel<LocationDTO> locations = locationService.getAllLocations(name, page, size);
        if(locations != null) {
            return ResponseEntity.ok(locations);
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get location by id in the Location Entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Location.class)) }),
            @ApiResponse(responseCode = "204", description = "No content",
                    content = @Content)})
    @GetMapping("/locations/{id}")
    public ResponseEntity getLocationById(@PathVariable("id") String id) {
        LocationDTO location = locationService.getLocationById(id);
        if(location != null) {
            return ResponseEntity.ok(location);
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Create a new Location in the Location Entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Location.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content) })
    @PostMapping("/locations")
    public ResponseEntity createLocation(@RequestBody Location location) {
        LocationDTO createdLocation = locationService.createLocation(location);
        return ResponseEntity.ok(createdLocation);
    }

    @Operation(summary = "Update location by id in the Location Entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Location.class)) }),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content) })
    @PutMapping("/locations/{id}")
    public ResponseEntity updateLocation(@PathVariable("id") String id, @RequestBody Location location) {
        LocationDTO updatedLocation = locationService.updateLocation(id, location);
        return ResponseEntity.ok(updatedLocation);
    }

    @Operation(summary = "Delete location by id in the Location Entity")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "Location deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content) })
    @DeleteMapping("/locations/{id}")
    public ResponseEntity deleteLocation(@PathVariable("id") String id) {
        return ResponseEntity.ok(locationService.deleteLocation(id));
    }

    @Operation(summary = "Delete all locations in the Location Entity")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "All locations deleted",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @DeleteMapping("/locations")
    public ResponseEntity deleteAllLocations() {
        return ResponseEntity.ok(locationService.deleteAllLocations());
    }


    @Operation(summary = "Get all locations in the Location Entity based on their active status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Locations found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Location.class)) }),
            @ApiResponse(responseCode = "204", description = "No content",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content) })
    @GetMapping("/locations/active/{active}")
    public ResponseEntity getByActive(
            @PathVariable("active") Boolean active,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        CollectionModel<LocationDTO> locations = locationService.getByActive(active, page, size);
        if(locations != null) {
            return ResponseEntity.ok(locations);
        }
        return ResponseEntity.noContent().build();
    }
}
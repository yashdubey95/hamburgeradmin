package com.example.hamburgeradmin.controller;

import com.example.hamburgeradmin.dto.LocationDTO;
import com.example.hamburgeradmin.dto.PartyReservationDTO;
import com.example.hamburgeradmin.model.Location;
import com.example.hamburgeradmin.model.PartyReservation;
import com.example.hamburgeradmin.services.PartyReservationServices;
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
 * This class exposes resources to interact with PartyReservation entity
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api")
@Log4j2
@Tag(name = "PartyReservation Controller", description = "Resources related to PartyReservation entity")
public class PartyReservationController {

    private final PartyReservationServices partyReservationServices;

    @Operation(summary = "Get all Reservations in the PartyReservation Entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservations found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PartyReservation.class)) }),
            @ApiResponse(responseCode = "204", description = "No content",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @GetMapping("/reservations")
    public ResponseEntity getAllReservations(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String partyType,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        CollectionModel<PartyReservationDTO> reservations = partyReservationServices.getAllReservations(name, partyType, page, size);
        if(reservations != null) {
            return ResponseEntity.ok(reservations);
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get Reservation by id in the PartyReservation Entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PartyReservation.class)) }),
            @ApiResponse(responseCode = "204", description = "No content",
                    content = @Content)})
    @GetMapping("/reservations/{id}")
    public ResponseEntity getReservationById(@PathVariable("id") String id) {
        PartyReservationDTO reservation = partyReservationServices.getReservationById(id);
        if(reservation != null) {
            return ResponseEntity.ok(reservation);
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Create a new Reservation in the PartyReservation Entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PartyReservation.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request Error",
                    content = @Content) })
    @PostMapping("/reservations")
    public ResponseEntity createReservation(@RequestBody PartyReservation reservation) {
        PartyReservationDTO createdReservation = partyReservationServices.createReservation(reservation);
        return ResponseEntity.ok(createdReservation);

    }

    @Operation(summary = "Update Reservation by id in the PartyReservation Entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PartyReservation.class)) }),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content) })
    @PutMapping("/reservations/{id}")
    public ResponseEntity updateReservation(@PathVariable("id") String id, @RequestBody PartyReservation reservation) {
        PartyReservationDTO updatedReservation = partyReservationServices.updateReservation(id, reservation);
        return ResponseEntity.ok(updatedReservation);
    }

    @Operation(summary = "Delete Reservation by id in the PartyReservation Entity")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "Reservation deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content) })
    @DeleteMapping("/reservations/{id}")
    public ResponseEntity deleteReservation(@PathVariable("id") String id) {
        return ResponseEntity.ok(partyReservationServices.deleteReservation(id));
    }

    @Operation(summary = "Delete all Reservations in the PartyReservation Entity")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "All Reservation deleted",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @DeleteMapping("/reservations")
    public ResponseEntity deleteReservations() {
        return ResponseEntity.ok(partyReservationServices.deleteReservations());
    }

    @Operation(summary = "Get all reservations in the PartyReservation Entity based on their location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Locations found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Location.class)) }),
            @ApiResponse(responseCode = "204", description = "No content",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content) })
    @GetMapping("/reservations/location/{locationName}")
    public ResponseEntity getByLocation(
            @PathVariable("active") String locationName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        CollectionModel<PartyReservationDTO> reservations = partyReservationServices.getByLocation(locationName, page, size);
        if(reservations != null) {
            return ResponseEntity.ok(reservations);
        }
        return ResponseEntity.noContent().build();
    }

}

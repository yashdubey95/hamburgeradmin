package com.example.hamburgeradmin.controller;

import com.example.hamburgeradmin.dto.MenuDTO;
import com.example.hamburgeradmin.model.Menu;
import com.example.hamburgeradmin.services.MenuServices;
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
 * This class exposes resources to interact with Menu entity
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api")
@Log4j2
@Tag(name = "Menu Controller", description = "Resources related to Menu entity")
public class MenuController {

    private final MenuServices menuServices;

    @Operation(summary = "Get all Menu Items in the Menu Entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Menu Items found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Menu.class)) }),
            @ApiResponse(responseCode = "204", description = "No content",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @GetMapping("/menus")
    public ResponseEntity getAllMenus(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        CollectionModel<MenuDTO> menu = menuServices.getAllMenus(name, category, page, size);
        if(menu != null) {
            return ResponseEntity.ok(menu);
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get Menu Item by id in the Menu Entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Menu Item found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Menu.class)) }),
            @ApiResponse(responseCode = "204", description = "No content",
                    content = @Content)})
    @GetMapping("/menus/{id}")
    public ResponseEntity getMenuById(@PathVariable("id") String id) {

        MenuDTO menu = menuServices.getMenuById(id);
        if(menu != null) {
            return ResponseEntity.ok(menu);
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Create a new Menu Item in the Menu Entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Menu Item created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Menu.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request Error",
                    content = @Content) })
    @PostMapping("/menus")
    public ResponseEntity createMenu(@RequestBody Menu menu) {
        MenuDTO menuItem = menuServices.createMenu(menu);
        if(menuItem != null) {
            return ResponseEntity.ok(menuItem);
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update Menu Item by id in the Menu Entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Menu Item updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Menu.class)) }),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content) })
    @PutMapping("/menus/{id}")
    public ResponseEntity updateMenu(@PathVariable("id") String id, @RequestBody Menu menu) {
        MenuDTO menuItem = menuServices.updateMenu(id, menu);
        if(menuItem != null) {
            return ResponseEntity.ok(menuItem);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete Menu Item by id in the Menu Entity")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "Menu Item deleted",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @DeleteMapping("/menus/{id}")
    public ResponseEntity deleteMenu(@PathVariable("id") String id) {

        return ResponseEntity.ok(menuServices.deleteMenu(id));
    }

    @Operation(summary = "Delete all Menu Items in the menu Entity")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "All Menu Items deleted",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @DeleteMapping("/menus")
    public ResponseEntity deleteAllMenus() {
        return ResponseEntity.ok(menuServices.deleteAllMenus());
    }

    @Operation(summary = "Get all Menu Items in the Menu Entity based on their comboAllowed status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Menu Items found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Menu.class)) }),
            @ApiResponse(responseCode = "204", description = "No content",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @GetMapping("/menus/comboAllowed/{combo}")
    public ResponseEntity getByCombo(
            @PathVariable("combo") Boolean combo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        CollectionModel<MenuDTO> menu = menuServices.getByCombo(combo, page, size);
        if(menu != null) {
            return ResponseEntity.ok(menu);
        }
        return ResponseEntity.noContent().build();
    }
}

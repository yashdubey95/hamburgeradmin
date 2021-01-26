package com.example.hamburgeradmin.controller;

import com.example.hamburgeradmin.model.Menu;
import com.example.hamburgeradmin.services.MenuServices;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Log4j2
public class MenuController {

    @Autowired
    MenuServices menuServices;

    @GetMapping("/menus")
    public ResponseEntity<List<Menu>> getAllMenus(@RequestParam(required = false) String name, @RequestParam(required = false) String category) {
        return menuServices.getAllMenus(name, category);
    }

    @GetMapping("/menus/{id}")
    public ResponseEntity<Menu> getMenuById(@PathVariable("id") String id) {
        return menuServices.getMenuById(id);
    }

    @PostMapping("/menus")
    public ResponseEntity<Menu> createMenu(@RequestBody Menu menu) {
        return menuServices.createMenu(menu);
    }

    @PutMapping("/menus/{id}")
    public ResponseEntity<Menu> updateMenu(@PathVariable("id") String id, @RequestBody Menu menu) {
        return menuServices.updateMenu(id, menu);
    }

    @DeleteMapping("/menus/{id}")
    public ResponseEntity<HttpStatus> deleteMenu(@PathVariable("id") String id) {
        return menuServices.deleteMenu(id);
    }

    @DeleteMapping("/menus")
    public ResponseEntity<HttpStatus> deleteAllMenus() {
        return menuServices.deleteAllMenus();
    }

    @GetMapping("/menus/comboAllowed/{combo}")
    public ResponseEntity<List<Menu>> getByCombo(@PathVariable("combo") Boolean combo) {
        return menuServices.getByCombo(combo);
    }
}

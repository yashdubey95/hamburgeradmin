package com.example.hamburgeradmin.services;

import com.example.hamburgeradmin.model.Menu;
import com.example.hamburgeradmin.repository.MenuRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class MenuServices {

    @Autowired
    MenuRepository menuRepository;

    public ResponseEntity<List<Menu>> getAllMenus(String name, String category) {
        try {
            List<Menu> menu = new ArrayList<>();

            if(name == null  && category == null)
                menuRepository.findAll().forEach(menu::add);
            else if(category == null)
                menuRepository.findByItemName(name).forEach(menu::add);
            else if(name == null)
                menuRepository.findByCategory(category).forEach(menu::add);

            if (menu.isEmpty()) {
                log.info("No content for Menu!");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(menu, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Menu> getMenuById(String id) {
        Optional<Menu> menuData = menuRepository.findById(id);

        if (menuData.isPresent()) {
            return new ResponseEntity<>(menuData.get(), HttpStatus.OK);
        } else {
            log.info("Menu with id: "+id+" not found!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Menu> createMenu(Menu menu) {
        try {
            System.out.println(menu.getComboAllowed());
            Menu _menu = menuRepository.save(menu);
            log.info("New menu created!");
            return new ResponseEntity<>(_menu, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Menu> updateMenu(String id, Menu menu) {
        Optional<Menu> menuData = menuRepository.findByMenuId(id);

        if (menuData.isPresent()) {
            Menu _menu = menuData.get();
            _menu.setCategory(menu.getCategory());
            _menu.setItemName(menu.getItemName());
            _menu.setItemPrice(menu.getItemPrice());
            _menu.setComboAllowed(menu.getComboAllowed());
            _menu.setComboPrice(menu.getComboPrice());
            return new ResponseEntity<>(menuRepository.save(_menu), HttpStatus.OK);
        } else {
            log.info("Menu with id: "+id+" not found!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deleteMenu(String id) {
        try {
            menuRepository.deleteById(id);
            log.info("Menu with id: "+id+" not found!");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<HttpStatus> deleteAllMenus() {
        try {
            menuRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Menu>> getByCombo(Boolean combo) {
        try {
            List<Menu> menu = new ArrayList<>();

            menuRepository.findByComboAllowed(combo).forEach(menu::add);

            if (menu.isEmpty()) {
                log.info("Menu with combo not found!");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(menu, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

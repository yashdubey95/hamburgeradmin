package com.example.hamburgeradmin.services;

import com.example.hamburgeradmin.assemblers.MenuAssembler;
import com.example.hamburgeradmin.dto.MenuDTO;
import com.example.hamburgeradmin.exception.BadRequestException;
import com.example.hamburgeradmin.exception.InternalServerErrorException;
import com.example.hamburgeradmin.exception.ResourceNotFoundException;
import com.example.hamburgeradmin.model.Menu;
import com.example.hamburgeradmin.repository.MenuRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author Yash Dubey
 * <p>
 * This class implements methods to fetch, store, update and delete records from Menu entity
 */
@AllArgsConstructor
@Service
@Log4j2
public class MenuServices {

    private final MenuRepository menuRepository;
    private final PagedResourcesAssembler pagedResourcesAssembler;
    private final MenuAssembler menuAssembler;

    public CollectionModel<MenuDTO> getAllMenus(String name, String category, int page, int size) {
        log.info("Entering getAllMenus method with name = {} and category = {}", name, category);
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Menu> pageMenu;

            if(name == null  && category == null)
                pageMenu = menuRepository.findAll(paging);
            else if(category == null)
                pageMenu = menuRepository.findByItemNameContaining(name,paging);
            else if(name == null)
                pageMenu = menuRepository.findByCategory(category, paging);
            else
                pageMenu = menuRepository.findByItemNameContainingAndCategory(name, category, paging);

            log.info("Finishing getAllMenus method");
            if(! CollectionUtils.isEmpty(pageMenu.getContent())) return pagedResourcesAssembler.toModel(pageMenu, menuAssembler);
            return null;

        } catch (Exception e) {
            log.error("Internal Server Error - getAllMenus method");
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public MenuDTO getMenuById(String id) {
        log.info("Entering getMenuById method with id = {}", id);
        Optional<Menu> menuData = menuRepository.findById(id);
        Menu menuItem = new Menu();
        log.info("Finishing getMenuById method");
        if (menuData.isPresent()) {
            menuItem = menuData.get();
            return menuAssembler.toModel(menuItem);
        }
        return null;
    }

    public MenuDTO createMenu(Menu menu) {
        log.info("Entering createMenu method with menu body = {}", menu);
        try {
            Menu menuItem = menuRepository.save(menu);
            log.info("Menu Item created with Menu id: {}", menu.getMenuId());
            log.info("Finishing createMenu method");
            return menuAssembler.toModel(menuItem);
        } catch (Exception e) {
            log.error("Bad Request Error - createMenu method");
            throw new BadRequestException("Internal Server Error");
        }
    }

    public MenuDTO updateMenu(String id, Menu menu) {
        log.info("Entering updateMenu method with id = {} and menu body = {}", id, menu);
        Optional<Menu> menuData = menuRepository.findByMenuId(id);
        Menu menuItemDTO;
        if (menuData.isPresent()) {
            Menu menuItem = menuData.get();
            menuItem.setCategory(menu.getCategory());
            menuItem.setItemName(menu.getItemName());
            menuItem.setItemPrice(menu.getItemPrice());
            menuItem.setComboAllowed(menu.getComboAllowed());
            menuItem.setComboPrice(menu.getComboPrice());
            menuItemDTO = menuRepository.save(menuItem);
            log.info("Menu Item updated with Menu id: {}", id);
            log.info("Finishing updateMenu method");
            return menuAssembler.toModel(menuItemDTO);
        }
        return null;
    }

    public String deleteMenu(String id) {
        log.info("Entering deleteMenu method with id = {}", id);
        if (menuRepository.existsById(id)){
            menuRepository.deleteById(id);
            log.info("Menu Item deleted with Menu id: {}", id);
            log.info("Finishing deleteMenu method");
            return "Menu Item with id: "+id+" deleted";
        }
        log.error("Menu with id: "+id+" does not exists");
        throw new ResourceNotFoundException("Menu", id);
    }

    public String deleteAllMenus() {
        log.info("Entering deleteAllMenus method");
        try{
            menuRepository.deleteAll();
            log.info("All Menu Items deleted");
            log.info("Finishing deleteAllMenus method");
            return "All Menu Items deleted";
        } catch (Exception e) {
            log.error("Internal Server Error - deleteAllMenus method");
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public CollectionModel<MenuDTO> getByCombo(Boolean combo, int page, int size) {
        log.info("Entering getByCombo method with combo = {}", combo);
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Menu> pageMenu = menuRepository.findByComboAllowed(true, paging);
            log.info("Finishing getByCombo method");
            if(! CollectionUtils.isEmpty(pageMenu.getContent())) return pagedResourcesAssembler.toModel(pageMenu, menuAssembler);
            return null;

        } catch (Exception e) {
            log.error("Internal Server Error - getByCombo method");
            throw new InternalServerErrorException("Internal Server Error");
        }
    }
}

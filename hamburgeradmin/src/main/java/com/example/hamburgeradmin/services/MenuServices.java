package com.example.hamburgeradmin.services;

import com.example.hamburgeradmin.assemblers.MenuAssembler;
import com.example.hamburgeradmin.dto.MenuDTO;
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
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Menu> pageMenu;

            if(name == null  && category == null)
                pageMenu = menuRepository.findAll(paging);
            else if(category == null)
                pageMenu = menuRepository.findByItemNameContaining(name,paging);
            else
                pageMenu = menuRepository.findByCategory(category, paging);

            if(! CollectionUtils.isEmpty(pageMenu.getContent())) return pagedResourcesAssembler.toModel(pageMenu, menuAssembler);
            return null;

        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public MenuDTO getMenuById(String id) {
        Optional<Menu> menuData = menuRepository.findById(id);
        Menu menuItem = new Menu();
        if (menuData.isPresent()) {
            menuItem = menuData.get();
            return menuAssembler.toModel(menuItem);
        }
        return null;
    }

    public MenuDTO createMenu(Menu menu) {
        try {
            Menu menuItem = menuRepository.save(menu);
            return menuAssembler.toModel(menuItem);
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public MenuDTO updateMenu(String id, Menu menu) {
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
            return menuAssembler.toModel(menuItemDTO);
        }
        return null;
    }

    public String deleteMenu(String id) {
        if (menuRepository.existsById(id)){
            menuRepository.deleteById(id);
            return "Menu Item with id: "+id+" deleted";
        }
        throw new ResourceNotFoundException("Menu", id);
    }

    public String deleteAllMenus() {
        menuRepository.deleteAll();
        return "All the Menu Items are deleted";
    }

    public CollectionModel<MenuDTO> getByCombo(Boolean combo, int page, int size) {
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Menu> pageMenu = menuRepository.findByComboAllowed(true, paging);

            if(! CollectionUtils.isEmpty(pageMenu.getContent())) return pagedResourcesAssembler.toModel(pageMenu, menuAssembler);
            return null;

        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }
}

package com.example.hamburgeradmin.assemblers;

import com.example.hamburgeradmin.dto.MenuDTO;
import com.example.hamburgeradmin.model.Menu;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

/**
 * @author Yash Dubey
 *
 * This class encapsulates data transfer object for Menu entity
 */
@AllArgsConstructor
@Component
public class MenuAssembler implements RepresentationModelAssembler<Menu, MenuDTO> {

    public MenuDTO toModel(Menu entity) {
        return new MenuDTO(entity.getItemName(), entity.getItemPrice(), entity.getComboAllowed(), entity.getComboPrice(), entity.getCategory());
    }
}

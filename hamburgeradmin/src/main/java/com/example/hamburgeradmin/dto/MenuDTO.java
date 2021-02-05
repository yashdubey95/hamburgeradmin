package com.example.hamburgeradmin.dto;

import com.example.hamburgeradmin.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "menu")
@Data
@AllArgsConstructor
public class MenuDTO extends RepresentationModel<MenuDTO> {
    private String itemName;
    private Double itemPrice;
    private Boolean comboAllowed;
    private Double comboPrice;
    private Category category;
}

package com.example.hamburgeradmin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "menu")
@Data
public class Menu extends Category{
    @Id
    private String menuId;
    private String itemName;
    private Double itemPrice;
    private Boolean comboAllowed;
    private Double comboPrice = 0.0;
}

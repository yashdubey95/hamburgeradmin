package com.example.hamburgeradmin.repository;

import com.example.hamburgeradmin.model.Menu;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository  extends MongoRepository<Menu, String> {
    List<Menu> findByCategory(String category);
    Optional<Menu> findByMenuId(String id);
    List<Menu> findByItemName(String name);
    List<Menu> findByComboAllowed(Boolean combo);
}

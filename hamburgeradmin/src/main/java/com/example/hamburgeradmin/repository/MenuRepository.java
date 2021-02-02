package com.example.hamburgeradmin.repository;

import com.example.hamburgeradmin.model.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository  extends MongoRepository<Menu, String> {
    Page<Menu> findByCategory(String category, Pageable pageable);
    Optional<Menu> findByMenuId(String id);
    Page<Menu> findByItemNameContaining(String name, Pageable pageable);
    Page<Menu> findByComboAllowed(Boolean combo, Pageable pageable);
    Page<Menu> findByItemNameContainingAndCategory(String name, String category, Pageable pageable);
}

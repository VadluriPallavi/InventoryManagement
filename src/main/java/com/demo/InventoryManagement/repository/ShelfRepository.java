package com.demo.InventoryManagement.repository;

import com.demo.InventoryManagement.entities.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShelfRepository extends JpaRepository<Shelf, Long> {
    Shelf findByShelfId(Long shelfId);
}

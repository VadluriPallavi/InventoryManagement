package com.demo.InventoryManagement.repository;

import com.demo.InventoryManagement.entities.Item;
import com.demo.InventoryManagement.entities.ItemQuantity;
import com.demo.InventoryManagement.entities.ItemQuantityKey;
import com.demo.InventoryManagement.entities.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemQuantityRepository extends JpaRepository<ItemQuantity, ItemQuantityKey> {
    ItemQuantity findByItemAndShelf(Optional<Item> item, Shelf shelf);
}

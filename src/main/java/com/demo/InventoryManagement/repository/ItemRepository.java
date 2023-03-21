package com.demo.InventoryManagement.repository;

import com.demo.InventoryManagement.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>, JpaSpecificationExecutor<Item> {
    Boolean existsByItemName(String itemName);

    Item findByItemName(String itemName);

}

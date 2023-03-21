package com.demo.InventoryManagement.services;

import com.demo.InventoryManagement.entities.Shelf;

import java.util.List;
import java.util.Map;

public interface ShelfService {
    public List<Shelf> getShelfs();
    public Shelf addShelf(Shelf shelf);
    public Shelf findShelfById(Long shelfId);

    public long remainingShelfSpace(Long shelfId);
}

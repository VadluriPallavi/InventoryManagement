package com.demo.InventoryManagement.services;

import com.demo.InventoryManagement.entities.ItemQuantity;
import com.demo.InventoryManagement.entities.Shelf;
import com.demo.InventoryManagement.repository.ShelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ShelfServiceImpl implements ShelfService {

    @Autowired
    private ShelfRepository shelfRepository;

    @Override
    public List<Shelf> getShelfs() {
        return shelfRepository.findAll();
    }

    public Shelf addShelf(Shelf shelf) {
        shelfRepository.save(shelf);
        return shelf;
    }

    @Override
    public Shelf findShelfById(Long shelfId) {
        return shelfRepository.findByShelfId(shelfId);
    }

    @Override
    public long remainingShelfSpace(Long shelfId) {
        Shelf shelf = shelfRepository.findByShelfId(shelfId);

        Long shelfCapacity = shelf.getShelfCapacity();
        List<ItemQuantity> itemQuantities = shelf.getQuantities();
        long itemsSpace = 0;
        for(ItemQuantity itemQuantity : itemQuantities) {
            long itemCapacityConsumption = itemQuantity.getItem().getCapacityConsumption();
            itemsSpace += itemCapacityConsumption * itemQuantity.getQuantity();
        }

        return shelfCapacity - itemsSpace;
    }
}

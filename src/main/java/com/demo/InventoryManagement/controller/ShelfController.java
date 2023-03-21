package com.demo.InventoryManagement.controller;


import com.demo.InventoryManagement.entities.Shelf;
import com.demo.InventoryManagement.services.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@CrossOrigin
@RestController
public class ShelfController {

    @Autowired
    private ShelfService shelfService;

    @GetMapping("/shelfs")
    public List<Shelf> getShelfs() {
        return this.shelfService.getShelfs();
    }

    @PostMapping("/shelfs")
    public Shelf addShelf(@RequestBody Shelf shelf) {
        return this.shelfService.addShelf(shelf);
    }

    @GetMapping("/shelf/space/{shelfId}")
    public Long remainingShelfSpace(@PathVariable long shelfId) {
        return this.shelfService.remainingShelfSpace(shelfId);
    }
}

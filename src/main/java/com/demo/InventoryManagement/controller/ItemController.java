package com.demo.InventoryManagement.controller;

import com.demo.InventoryManagement.dto.RequestDto;
import com.demo.InventoryManagement.entities.FilterList;
import com.demo.InventoryManagement.entities.Item;
import com.demo.InventoryManagement.repository.ItemRepository;
import com.demo.InventoryManagement.services.FilterSpecification;
import com.demo.InventoryManagement.services.ItemService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private FilterSpecification<Item> itemFilterSpecification;

    @GetMapping("/items")
    public List<Item> getItems() {
        return this.itemService.getItems();
    }

    @GetMapping("/items/{itemId}")
    public Item getItem(@PathVariable long itemId) {
        return this.itemService.getItem(itemId);
    }

    @PostMapping("/items")
    public Item addItem(@RequestBody Item item) {
        return this.itemService.addItem(item);
    }


    @PutMapping("/items/{itemId}/{shelfId}/{quantity}")
    public Optional<Item> addMoreItems(@PathVariable long itemId, @PathVariable long shelfId, @PathVariable long quantity) {
        return this.itemService.addMoreItems(itemId, shelfId, quantity);
    }

    @PutMapping("/items")
    public  Item updateItem(@RequestBody Item item) {
        return this.itemService.updateItem(item);
    }

    @PatchMapping("/items/{itemId}")
    public Item updateItemFields(@PathVariable String itemId, @RequestBody Map<String, Object> fields) {
        return this.itemService.updateItemFields(Long.parseLong(itemId), fields);
    }


    @PostMapping("/items/filterspecification")
    public List<Item> getItemsByFilter(@RequestBody RequestDto requestDto) {
        Specification<Item> searchSpecification = itemFilterSpecification.getSearchSpecification(requestDto.getSearchRequestDto(), requestDto.getGlobalOperator());

        return itemRepository.findAll(searchSpecification);
    }

    @DeleteMapping("/items/{itemId}")
    public void deleteItem(@PathVariable String itemId) {
        this.itemService.deleteItem(Long.parseLong(itemId));
    }

    @PostMapping("/items/deduct/{itemId}/{shelfId}/{requestedQuantity}")
    public Optional<Item> deductItemQuantity(@PathVariable long itemId, @PathVariable long shelfId, @PathVariable long requestedQuantity) {
        return this.itemService.deductItemQuantity(itemId, shelfId, requestedQuantity);
    }
}

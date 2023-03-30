package com.demo.InventoryManagement.controller;

import com.demo.InventoryManagement.dto.RequestDto;
import com.demo.InventoryManagement.entities.FilterList;
import com.demo.InventoryManagement.entities.Item;
import com.demo.InventoryManagement.exceptions.CustomErrorException;
import com.demo.InventoryManagement.repository.ItemRepository;
import com.demo.InventoryManagement.services.FilterSpecification;
import com.demo.InventoryManagement.services.ItemService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Item>> getItems() {

        try {
            List<Item> items = this.itemService.getItems();
            if (items.size() == 0) {
                throw new CustomErrorException(
                        HttpStatus.NOT_FOUND,
                        "There are no items found"
                );
            }
            return ResponseEntity.of(Optional.of(items));
        } catch (CustomErrorException e) {
            throw new CustomErrorException(
                    HttpStatus.NOT_FOUND,
                    e.getMessage()
            );
        } catch (Exception e) {
            throw new CustomErrorException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage()
            );
        }
    }

    @GetMapping("/items/{itemId}")
    public Item getItem(@PathVariable long itemId) {
        return this.itemService.getItem(itemId);
    }

    @PostMapping("/items")
    public ResponseEntity<Item> addItem(@RequestBody Item item) {
        Item item1 = null;

        try {
            if (itemRepository.existsByItemName(item.getItemName())) {
                throw new CustomErrorException(
                        HttpStatus.ALREADY_REPORTED,
                        "Item already exists",
                        item
                );
            }
            item1 = this.itemService.addItem(item);
            return ResponseEntity.of(Optional.of(item1));
        } catch (CustomErrorException e) {
            throw new CustomErrorException(
                    HttpStatus.ALREADY_REPORTED,
                    "Item already exists",
                    item
            );
        } catch (Exception e) {
            throw new CustomErrorException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error in adding item",
                    item
            );
        }
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

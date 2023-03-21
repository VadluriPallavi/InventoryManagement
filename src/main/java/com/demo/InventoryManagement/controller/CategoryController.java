package com.demo.InventoryManagement.controller;


import com.demo.InventoryManagement.constants.StatusCode;
import com.demo.InventoryManagement.entities.Category;
import com.demo.InventoryManagement.models.Response;
import com.demo.InventoryManagement.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categories = this.categoryService.getCategories();
        if (categories.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(categories));
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable long categoryId) {
        Category category = this.categoryService.getCategory(categoryId);
        if (category == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(category));
    }

    @PostMapping("/categories")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        Category addedCategory = null;
        try {
            addedCategory = this.categoryService.addCategory(category);
            return ResponseEntity.of(Optional.of(addedCategory));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable String categoryId) {
        try {
            this.categoryService.deleteCategory(Long.parseLong(categoryId));
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

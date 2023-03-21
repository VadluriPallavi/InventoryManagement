package com.demo.InventoryManagement.services;

import com.demo.InventoryManagement.entities.Category;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    public List<Category> getCategories();

    public Category getCategory(long categoryId);
    public Category addCategory(Category category);

    public void deleteCategory(long categoryId);
}

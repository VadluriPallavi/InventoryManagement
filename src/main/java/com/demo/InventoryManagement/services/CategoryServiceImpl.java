package com.demo.InventoryManagement.services;

import com.demo.InventoryManagement.entities.Category;
import com.demo.InventoryManagement.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(long categoryId) {
        Category category = null;
       try {
           category = categoryRepository.findById(categoryId).get();
       } catch (Exception e) {
           e.printStackTrace();
       }
       return category;
    }

    @Override
    public Category addCategory(Category category) {
        categoryRepository.save(category);
        return category;
    }

    @Override
    public void deleteCategory(long categoryId) {
        Category category = categoryRepository.findById(categoryId).get();
        categoryRepository.delete(category);
    }
}

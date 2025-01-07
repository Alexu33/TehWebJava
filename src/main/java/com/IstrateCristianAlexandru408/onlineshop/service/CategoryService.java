package com.IstrateCristianAlexandru408.onlineshop.service;

import com.IstrateCristianAlexandru408.onlineshop.dto.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> getAllCategories();

    public Category getCategoryById(Long id);

    public Category createCategory(Category category);

    public Category updateCategory(Category category);

    public void deleteCategory(Long id);
}

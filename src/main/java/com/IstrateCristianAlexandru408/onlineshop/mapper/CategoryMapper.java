package com.IstrateCristianAlexandru408.onlineshop.mapper;

import com.IstrateCristianAlexandru408.onlineshop.dto.Category;
import com.IstrateCristianAlexandru408.onlineshop.entity.CategoryEntity;

public class CategoryMapper {
    private static CategoryMapper instance;

    private CategoryMapper() {
    }

    public static CategoryMapper getInstance() {
        if (instance == null) {
            instance = new CategoryMapper();
        }
        return instance;
    }

    public Category toDto(CategoryEntity categoryEntity) {
        Category category = new Category();
        category.setId(categoryEntity.getId());
        category.setName(categoryEntity.getName());
        return category;
    }

    public CategoryEntity toEntity(Category category) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(category.getId());
        categoryEntity.setName(category.getName());
        return categoryEntity;
    }


}

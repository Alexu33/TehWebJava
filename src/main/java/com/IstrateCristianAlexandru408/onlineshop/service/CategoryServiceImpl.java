package com.IstrateCristianAlexandru408.onlineshop.service;

import com.IstrateCristianAlexandru408.onlineshop.dto.Category;
import com.IstrateCristianAlexandru408.onlineshop.entity.CategoryEntity;
import com.IstrateCristianAlexandru408.onlineshop.exception.CategoryAlreadyExistsException;
import com.IstrateCristianAlexandru408.onlineshop.exception.CategoryNotFoundException;
import com.IstrateCristianAlexandru408.onlineshop.mapper.CategoryMapper;
import com.IstrateCristianAlexandru408.onlineshop.repository.CategoryRepository;
import com.IstrateCristianAlexandru408.onlineshop.util.ErrorMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryMapper.getInstance()::toDto)
                .collect(Collectors.toList());
    }

    public Category getCategoryById(Long id) {

        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(String.format(ErrorMessageUtils.CATEGORY_NOT_FOUND, id)));

        return CategoryMapper.getInstance().toDto(categoryEntity);
    }

    public Category createCategory(Category category) {
        if(categoryRepository.existsByName(category.getName()))
        {
            throw new CategoryAlreadyExistsException(String.format(ErrorMessageUtils.CATEGORY_ALREADY_EXISTS, category.getName()));
        }

        CategoryEntity categoryEntity = categoryRepository.save(CategoryMapper.getInstance().toEntity(category));
        return CategoryMapper.getInstance().toDto(categoryEntity);
    }

    public Category updateCategory(Category category) {
        CategoryEntity categoryEntity =  categoryRepository.findById(category.getId()).orElseThrow(() -> new CategoryNotFoundException(String.format(ErrorMessageUtils.CATEGORY_NOT_FOUND, category.getId())));

        if(!categoryEntity.getName().equals(category.getName()))
        {
            if(categoryRepository.existsByName(category.getName()))
            {
                throw new CategoryAlreadyExistsException(String.format(ErrorMessageUtils.CATEGORY_ALREADY_EXISTS, category.getName()));
            }
        }
        CategoryEntity savedCategoryEntity = categoryRepository.save(CategoryMapper.getInstance().toEntity(category));
        return CategoryMapper.getInstance().toDto(savedCategoryEntity);
    }

    public void deleteCategory(Long id) {
        categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(String.format(ErrorMessageUtils.CATEGORY_NOT_FOUND, id)));
        categoryRepository.deleteById(id);
    }


}

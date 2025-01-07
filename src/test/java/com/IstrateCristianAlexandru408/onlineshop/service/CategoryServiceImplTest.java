package com.IstrateCristianAlexandru408.onlineshop.service;

import com.IstrateCristianAlexandru408.onlineshop.dto.Category;
import com.IstrateCristianAlexandru408.onlineshop.entity.CategoryEntity;
import com.IstrateCristianAlexandru408.onlineshop.exception.CategoryAlreadyExistsException;
import com.IstrateCristianAlexandru408.onlineshop.exception.CategoryNotFoundException;
import com.IstrateCristianAlexandru408.onlineshop.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private Category category;
    private CategoryEntity categoryEntity;

    @BeforeEach
    public void setUp() {
        category = new Category();
        category.setId(1L);
        category.setName("Electronics");

        categoryEntity = new CategoryEntity();
        categoryEntity.setId(1L);
        categoryEntity.setName("Electronics");
    }

    @Test
    public void testGetCategoryById_Success() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(categoryEntity));

        Category result = categoryService.getCategoryById(1L);

        assertNotNull(result);
        assertEquals("Electronics", result.getName());
    }

    @Test
    public void testGetCategoryById_NotFound() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> categoryService.getCategoryById(1L));
    }

    @Test
    public void testCreateCategory_Success() {
        when(categoryRepository.existsByName("Electronics")).thenReturn(false);
        when(categoryRepository.save(any(CategoryEntity.class))).thenReturn(categoryEntity);

        Category result = categoryService.createCategory(category);

        assertNotNull(result);
        assertEquals("Electronics", result.getName());
    }

    @Test
    public void testCreateCategory_AlreadyExists() {
        when(categoryRepository.existsByName("Electronics")).thenReturn(true);

        assertThrows(CategoryAlreadyExistsException.class, () -> categoryService.createCategory(category));
    }

    @Test
    public void testUpdateCategory_Success() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(categoryEntity));
        when(categoryRepository.save(any(CategoryEntity.class))).thenReturn(categoryEntity);

        Category result = categoryService.updateCategory(category);

        assertNotNull(result);
        assertEquals("Electronics", result.getName());
    }

    @Test
    public void testUpdateCategory_NotFound() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> categoryService.updateCategory(category));
    }

    @Test
    public void testDeleteCategory_Success() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(categoryEntity));

        categoryService.deleteCategory(1L);

        verify(categoryRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteCategory_NotFound() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> categoryService.deleteCategory(1L));
    }
}
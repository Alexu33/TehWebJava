package com.IstrateCristianAlexandru408.onlineshop.service;

import com.IstrateCristianAlexandru408.onlineshop.dto.Product;
import com.IstrateCristianAlexandru408.onlineshop.entity.CategoryEntity;
import com.IstrateCristianAlexandru408.onlineshop.entity.ProductEntity;
import com.IstrateCristianAlexandru408.onlineshop.exception.ProductNotFoundException;
import com.IstrateCristianAlexandru408.onlineshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private ProductEntity mockProductEntity;
    private Product mockProduct;

    @BeforeEach
    void setUp() {
        CategoryEntity mockCategory = new CategoryEntity();
        mockCategory.setId(1L);
        mockCategory.setName("Test Category");

        mockProductEntity = new ProductEntity();
        mockProductEntity.setId(1L);
        mockProductEntity.setName("Test Product");
        mockProductEntity.setPrice(new BigDecimal("100.0"));
        mockProductEntity.setDescription("Test description");
        mockProductEntity.setStockQuantity(10);
        mockProductEntity.setCategory(mockCategory);

        mockProduct = new Product(1L, "Test Product", "Test description", new BigDecimal("100.0"), 10, 1L);
    }

    @Test
    void testGetAllProducts() {
        when(productRepository.findAll()).thenReturn(List.of(mockProductEntity));

        List<Product> products = productService.getAllProducts();

        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals(mockProduct.getName(), products.get(0).getName());
    }

    @Test
    void testGetProductById_Success() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(mockProductEntity));

        Product product = productService.getProductById(1L);

        assertNotNull(product);
        assertEquals(mockProduct.getName(), product.getName());
    }

    @Test
    void testGetProductById_ProductNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> {
            productService.getProductById(1L);
        });

        assertEquals("Product with id 1 Not Found", exception.getMessage());
    }

    @Test
    void testCreateProduct() {
        when(productRepository.save(any(ProductEntity.class))).thenReturn(mockProductEntity);

        Product createdProduct = productService.createProduct(mockProduct);

        assertNotNull(createdProduct);
        assertEquals(mockProduct.getName(), createdProduct.getName());
    }

    @Test
    void testUpdateProduct_Success() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(mockProductEntity));
        when(productRepository.save(any(ProductEntity.class))).thenReturn(mockProductEntity);

        Product updatedProduct = productService.updateProduct(mockProduct);

        assertNotNull(updatedProduct);
        assertEquals(mockProduct.getName(), updatedProduct.getName());
    }

    @Test
    void testUpdateProduct_ProductNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> {
            productService.updateProduct(mockProduct);
        });

        assertEquals("Product with id 1 Not Found", exception.getMessage());
    }

    @Test
    void testDeleteProduct_Success() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(mockProductEntity));

        productService.deleteProduct(1L);

        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteProduct_ProductNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> {
            productService.deleteProduct(1L);
        });

        assertEquals("Product with id 1 Not Found", exception.getMessage());
    }
}

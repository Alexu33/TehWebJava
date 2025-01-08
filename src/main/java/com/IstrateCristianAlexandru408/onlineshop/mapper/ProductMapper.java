package com.IstrateCristianAlexandru408.onlineshop.mapper;

import com.IstrateCristianAlexandru408.onlineshop.dto.Product;
import com.IstrateCristianAlexandru408.onlineshop.entity.CategoryEntity;
import com.IstrateCristianAlexandru408.onlineshop.entity.ProductEntity;

public class ProductMapper {
    private static ProductMapper instance;

    private ProductMapper() {
    }

    public static ProductMapper getInstance() {
        if (instance == null) {
            instance = new ProductMapper();
        }
        return instance;
    }

    public Product toDto(ProductEntity productEntity) {
        Product product = new Product();
        product.setId(productEntity.getId());
        product.setName(productEntity.getName());
        product.setDescription(productEntity.getDescription());
        product.setPrice(productEntity.getPrice());
        product.setStockQuantity(productEntity.getStockQuantity());
        product.setCategoryId(productEntity.getCategory().getId());
        return product;
    }

    public ProductEntity toEntity(Product product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(product.getId());
        productEntity.setName(product.getName());
        productEntity.setDescription(product.getDescription());
        productEntity.setPrice(product.getPrice());
        productEntity.setStockQuantity(product.getStockQuantity());
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(product.getCategoryId());
        productEntity.setCategory(categoryEntity);
        return productEntity;
    }

}

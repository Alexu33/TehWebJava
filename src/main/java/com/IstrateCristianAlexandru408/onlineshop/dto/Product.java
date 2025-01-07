package com.IstrateCristianAlexandru408.onlineshop.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class Product {
    private Long id;
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name should be max 100 characters")
    private String name;

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 100, message = "Description should be at least 10 characters and max 100")
    private String description;

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be a positive number")
    private BigDecimal price;

    @NotNull(message = "stockQuantity is required")
    @Min(value = 0, message = "Stock quantity must be a positive number")
    private Integer stockQuantity;

    @NotNull(message = "Category Id is required")
    private Long categoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
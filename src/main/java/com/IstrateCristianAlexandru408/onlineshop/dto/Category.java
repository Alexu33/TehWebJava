package com.IstrateCristianAlexandru408.onlineshop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Category {
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "Name should be max 50 characters")
    private String name;

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
}

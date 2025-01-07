package com.IstrateCristianAlexandru408.onlineshop.repository;

import com.IstrateCristianAlexandru408.onlineshop.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    public boolean existsByName(String name);

}
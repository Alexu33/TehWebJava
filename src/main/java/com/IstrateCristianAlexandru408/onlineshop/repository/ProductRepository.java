package com.IstrateCristianAlexandru408.onlineshop.repository;

import com.IstrateCristianAlexandru408.onlineshop.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
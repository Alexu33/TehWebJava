package com.IstrateCristianAlexandru408.onlineshop.repository;

import com.IstrateCristianAlexandru408.onlineshop.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    List<ReviewEntity> findByProductId(Long productId);
    List<ReviewEntity> findByUserId(Long userId);
}
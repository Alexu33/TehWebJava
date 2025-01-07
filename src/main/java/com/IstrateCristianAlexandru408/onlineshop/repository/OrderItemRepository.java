package com.IstrateCristianAlexandru408.onlineshop.repository;

import com.IstrateCristianAlexandru408.onlineshop.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {
}

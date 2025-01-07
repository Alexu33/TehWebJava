package com.IstrateCristianAlexandru408.onlineshop.repository;

import com.IstrateCristianAlexandru408.onlineshop.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}

package com.IstrateCristianAlexandru408.onlineshop.mapper;

import com.IstrateCristianAlexandru408.onlineshop.dto.OrderItem;
import com.IstrateCristianAlexandru408.onlineshop.entity.OrderItemEntity;

public class OrderItemMapper {
    private static OrderItemMapper instance;
    private OrderItemMapper() {}
    public static OrderItemMapper getInstance() {
        if (instance == null) {
            instance = new OrderItemMapper();
        }
        return instance;
    }

    public OrderItem toDto(OrderItemEntity orderItemEntity) {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItemEntity.getId());
        orderItem.setQuantity(orderItemEntity.getQuantity());
        orderItem.setPrice(orderItemEntity.getPrice());
        return orderItem;
    }

    public OrderItemEntity toEntity(OrderItem orderItem) {
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setId(orderItem.getId());
        orderItemEntity.setQuantity(orderItem.getQuantity());
        orderItemEntity.setPrice(orderItem.getPrice());
        return orderItemEntity;
    }

}

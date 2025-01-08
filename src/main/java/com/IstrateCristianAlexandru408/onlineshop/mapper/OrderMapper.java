package com.IstrateCristianAlexandru408.onlineshop.mapper;

import com.IstrateCristianAlexandru408.onlineshop.dto.Order;
import com.IstrateCristianAlexandru408.onlineshop.entity.OrderEntity;
import com.IstrateCristianAlexandru408.onlineshop.entity.UserEntity;

import java.util.stream.Collectors;

public class OrderMapper {
    private static OrderMapper instance;

    private OrderMapper() {
    }

    public static OrderMapper getInstance() {
        if (instance == null) {
            instance = new OrderMapper();
        }
        return instance;
    }

    public Order toDto(OrderEntity orderEntity) {
        Order order = new Order();
        order.setId(orderEntity.getId());
        order.setUserId(orderEntity.getUser().getId());
        order.setOrderDate(orderEntity.getOrderDate());
        order.setStatus(orderEntity.getStatus());
        order.setOrderItems(orderEntity.getOrderItems().
                stream()
                .map(OrderItemMapper.getInstance()::toDto).
                collect(Collectors.toList()));
        return order;
    }

    public OrderEntity toEntity(Order order) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(order.getId());
        orderEntity.setOrderDate(order.getOrderDate());
        orderEntity.setStatus(order.getStatus());
        UserEntity userEntity = new UserEntity();
        userEntity.setId(order.getUserId());
        orderEntity.setUser(userEntity);
        orderEntity.setOrderItems(
                order.getOrderItems()
                        .stream()
                        .map(OrderItemMapper.getInstance()::toEntity)
                        .collect(Collectors.toList()));
        return orderEntity;
    }


}

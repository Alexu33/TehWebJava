package com.IstrateCristianAlexandru408.onlineshop.service;

import com.IstrateCristianAlexandru408.onlineshop.dto.OrderItem;

import java.util.List;

public interface OrderItemService {

    public List<OrderItem> getAllOrderItems();

    public OrderItem getOrderItemById(Long id);

    public OrderItem createOrderItem(OrderItem orderItem);

    public OrderItem updateOrderItem(OrderItem orderItem);

    public void deleteOrderItem(Long id);
}

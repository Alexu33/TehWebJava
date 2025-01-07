package com.IstrateCristianAlexandru408.onlineshop.service;

import com.IstrateCristianAlexandru408.onlineshop.dto.Order;

import java.util.List;

public interface OrderService {
    public List<Order> getAllOrders();

    public Order getOrderById(Long id);

    public Order createOrder(Order order);

    public Order updateOrder(Order order);

    public void deleteOrder(Long id);
}

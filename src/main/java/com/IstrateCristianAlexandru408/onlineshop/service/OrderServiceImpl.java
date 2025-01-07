package com.IstrateCristianAlexandru408.onlineshop.service;

import com.IstrateCristianAlexandru408.onlineshop.dto.Order;
import com.IstrateCristianAlexandru408.onlineshop.entity.OrderEntity;
import com.IstrateCristianAlexandru408.onlineshop.exception.OrderNotFoundException;
import com.IstrateCristianAlexandru408.onlineshop.mapper.OrderMapper;
import com.IstrateCristianAlexandru408.onlineshop.repository.OrderRepository;
import com.IstrateCristianAlexandru408.onlineshop.util.ErrorMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(OrderMapper.getInstance()::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Order getOrderById(Long id) {
        OrderEntity orderEntity = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(String.format(ErrorMessageUtils.ORDER_NOT_FOUND, id)));
        return OrderMapper.getInstance().toDto(orderEntity);
    }

    @Override
    public Order createOrder(Order order) {
        OrderEntity orderEntity = orderRepository.save(OrderMapper.getInstance().toEntity(order));
        return OrderMapper.getInstance().toDto(orderEntity);
    }

    @Override
    public Order updateOrder(Order order) {
        orderRepository.findById(order.getId())
                .orElseThrow(() -> new OrderNotFoundException(String.format(ErrorMessageUtils.ORDER_NOT_FOUND, order.getId())));
        OrderEntity orderEntity = orderRepository.save(OrderMapper.getInstance().toEntity(order));
        return OrderMapper.getInstance().toDto(orderEntity);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(String.format(ErrorMessageUtils.ORDER_NOT_FOUND, id)));
        orderRepository.deleteById(id);
    }
}
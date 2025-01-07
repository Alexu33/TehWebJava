package com.IstrateCristianAlexandru408.onlineshop.service;

import com.IstrateCristianAlexandru408.onlineshop.dto.OrderItem;
import com.IstrateCristianAlexandru408.onlineshop.entity.OrderItemEntity;
import com.IstrateCristianAlexandru408.onlineshop.exception.OrderItemNotFoundException;
import com.IstrateCristianAlexandru408.onlineshop.mapper.OrderItemMapper;
import com.IstrateCristianAlexandru408.onlineshop.repository.OrderItemRepository;
import com.IstrateCristianAlexandru408.onlineshop.util.ErrorMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    OrderItemRepository orderItemRepository;


    @Override
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll()
                .stream()
                .map(OrderItemMapper.getInstance()::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderItem getOrderItemById(Long id) {
        OrderItemEntity orderItemEntity = orderItemRepository.findById(id)
                .orElseThrow(() -> new OrderItemNotFoundException(String.format(ErrorMessageUtils.ORDER_ITEM_NOT_FOUND, id)));

        return OrderItemMapper.getInstance().toDto(orderItemEntity);
    }

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        OrderItemEntity orderItemEntity = orderItemRepository.save(OrderItemMapper.getInstance().toEntity(orderItem));
        return OrderItemMapper.getInstance().toDto(orderItemEntity);
    }

    @Override
    public OrderItem updateOrderItem(OrderItem orderItem) {
        orderItemRepository.findById(orderItem.getId())
                .orElseThrow(() -> new OrderItemNotFoundException(String.format(ErrorMessageUtils.ORDER_ITEM_NOT_FOUND, orderItem.getId())));
        OrderItemEntity orderItemEntity = orderItemRepository.save(OrderItemMapper.getInstance().toEntity(orderItem));
        return OrderItemMapper.getInstance().toDto(orderItemEntity);
    }

    @Override
    public void deleteOrderItem(Long id) {
        orderItemRepository.findById(id)
                .orElseThrow(() -> new OrderItemNotFoundException(String.format(ErrorMessageUtils.ORDER_ITEM_NOT_FOUND, id)));
        orderItemRepository.deleteById(id);
    }
}

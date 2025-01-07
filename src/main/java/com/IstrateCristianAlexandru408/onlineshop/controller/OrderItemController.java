package com.IstrateCristianAlexandru408.onlineshop.controller;

import com.IstrateCristianAlexandru408.onlineshop.dto.OrderItem;
import com.IstrateCristianAlexandru408.onlineshop.service.OrderItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderitems")
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public List<OrderItem> getOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    @GetMapping("/{id}")
    public OrderItem getOrderItem(@PathVariable Long id) {
        return orderItemService.getOrderItemById(id);
    }

    @PostMapping
    public OrderItem createOrderItem(@RequestBody @Valid OrderItem orderItem) {
        return orderItemService.createOrderItem(orderItem);
    }

    @PutMapping("/{id}")
    public OrderItem updateOrderItem(@PathVariable Long id, @RequestBody @Valid OrderItem orderItem) {
        orderItem.setId(id);
        return orderItemService.updateOrderItem(orderItem);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);
    }


}

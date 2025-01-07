package com.IstrateCristianAlexandru408.onlineshop.service;

import com.IstrateCristianAlexandru408.onlineshop.dto.Order;
import com.IstrateCristianAlexandru408.onlineshop.entity.OrderEntity;
import com.IstrateCristianAlexandru408.onlineshop.entity.UserEntity;
import com.IstrateCristianAlexandru408.onlineshop.exception.OrderNotFoundException;
import com.IstrateCristianAlexandru408.onlineshop.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    private OrderEntity mockOrderEntity;
    private Order mockOrder;

    @BeforeEach
    void setUp() {
        UserEntity mockUserEntity = new UserEntity();
        mockUserEntity.setId(1L);
        mockUserEntity.setUsername("Test User");

        mockOrderEntity = new OrderEntity();
        mockOrderEntity.setId(1L);
        mockOrderEntity.setOrderDate(LocalDateTime.now());
        mockOrderEntity.setStatus("PENDING");
        mockOrderEntity.setUser(mockUserEntity);

        mockOrder = new Order();
        mockOrder.setId(1L);
        mockOrder.setUserId(1L);
        mockOrder.setOrderDate(LocalDateTime.now());
        mockOrder.setStatus("PENDING");
        mockOrder.setOrderItems(List.of());
    }

    @Test
    void testGetAllOrders() {
        when(orderRepository.findAll()).thenReturn(List.of(mockOrderEntity));

        List<Order> orders = orderService.getAllOrders();

        assertNotNull(orders);
        assertEquals(1, orders.size());
        assertEquals(mockOrder.getStatus(), orders.get(0).getStatus());
    }

    @Test
    void testGetOrderById_Success() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(mockOrderEntity));

        Order order = orderService.getOrderById(1L);

        assertNotNull(order);
        assertEquals(mockOrder.getStatus(), order.getStatus());
    }

    @Test
    void testGetOrderById_OrderNotFound() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        OrderNotFoundException exception = assertThrows(OrderNotFoundException.class, () -> {
            orderService.getOrderById(1L);
        });

        assertEquals("Order with id 1 Not Found", exception.getMessage());
    }

    @Test
    void testCreateOrder() {
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(mockOrderEntity);

        Order createdOrder = orderService.createOrder(mockOrder);

        assertNotNull(createdOrder);
        assertEquals(mockOrder.getStatus(), createdOrder.getStatus());
    }

    @Test
    void testUpdateOrder_Success() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(mockOrderEntity));
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(mockOrderEntity);

        Order updatedOrder = orderService.updateOrder(mockOrder);

        assertNotNull(updatedOrder);
        assertEquals(mockOrder.getStatus(), updatedOrder.getStatus());
    }

    @Test
    void testUpdateOrder_OrderNotFound() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        OrderNotFoundException exception = assertThrows(OrderNotFoundException.class, () -> {
            orderService.updateOrder(mockOrder);
        });

        assertEquals("Order with id 1 Not Found", exception.getMessage());
    }

    @Test
    void testDeleteOrder_Success() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(mockOrderEntity));

        orderService.deleteOrder(1L);

        verify(orderRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteOrder_OrderNotFound() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        OrderNotFoundException exception = assertThrows(OrderNotFoundException.class, () -> {
            orderService.deleteOrder(1L);
        });

        assertEquals("Order with id 1 Not Found", exception.getMessage());
    }
}

package com.IstrateCristianAlexandru408.onlineshop.service;

import com.IstrateCristianAlexandru408.onlineshop.dto.OrderItem;
import com.IstrateCristianAlexandru408.onlineshop.entity.OrderEntity;
import com.IstrateCristianAlexandru408.onlineshop.entity.OrderItemEntity;
import com.IstrateCristianAlexandru408.onlineshop.entity.ProductEntity;
import com.IstrateCristianAlexandru408.onlineshop.exception.OrderItemNotFoundException;
import com.IstrateCristianAlexandru408.onlineshop.repository.OrderItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class OrderItemServiceImplTest {

    @Mock
    private OrderItemRepository orderItemRepository;

    @InjectMocks
    private OrderItemServiceImpl orderItemService;

    private OrderItem orderItem;
    private OrderItemEntity orderItemEntity;

    @BeforeEach
    public void setUp() {
        orderItem = new OrderItem();
        orderItem.setId(1L);
        orderItem.setOrderId(1L);
        orderItem.setProductId(1L);
        orderItem.setQuantity(2);
        orderItem.setPrice(BigDecimal.valueOf(20.00));

        orderItemEntity = new OrderItemEntity();
        orderItemEntity.setId(1L);
        orderItemEntity.setOrder(new OrderEntity());
        orderItemEntity.setProduct(new ProductEntity());
        orderItemEntity.setQuantity(2);
        orderItemEntity.setPrice(BigDecimal.valueOf(20.00));
    }

    @Test
    public void testGetOrderItemById_Success() {
        when(orderItemRepository.findById(1L)).thenReturn(Optional.of(orderItemEntity));

        OrderItem result = orderItemService.getOrderItemById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testGetOrderItemById_NotFound() {
        when(orderItemRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(OrderItemNotFoundException.class, () -> orderItemService.getOrderItemById(1L));
    }

    @Test
    public void testCreateOrderItem_Success() {
        when(orderItemRepository.save(any(OrderItemEntity.class))).thenReturn(orderItemEntity);

        OrderItem result = orderItemService.createOrderItem(orderItem);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testUpdateOrderItem_Success() {
        when(orderItemRepository.findById(1L)).thenReturn(Optional.of(orderItemEntity));
        when(orderItemRepository.save(any(OrderItemEntity.class))).thenReturn(orderItemEntity);

        OrderItem result = orderItemService.updateOrderItem(orderItem);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testUpdateOrderItem_NotFound() {
        when(orderItemRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(OrderItemNotFoundException.class, () -> orderItemService.updateOrderItem(orderItem));
    }

    @Test
    public void testDeleteOrderItem_Success() {
        when(orderItemRepository.findById(1L)).thenReturn(Optional.of(orderItemEntity));

        orderItemService.deleteOrderItem(1L);

        verify(orderItemRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteOrderItem_NotFound() {
        when(orderItemRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(OrderItemNotFoundException.class, () -> orderItemService.deleteOrderItem(1L));
    }
}

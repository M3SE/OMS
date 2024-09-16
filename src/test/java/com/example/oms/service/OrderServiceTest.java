package com.example.oms.service;

import com.example.oms.entity.Order;
import com.example.oms.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createOrder() {
        Order order = new Order();
        order.setCustomerName("John Doe");

        when(orderRepository.save(order)).thenReturn(order);

        Order createdOrder = orderService.saveOrder(order);

        assertNotNull(createdOrder);
        assertEquals("John Doe", createdOrder.getCustomerName());
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void getOrderById() {
        Long orderId = 1L;
        Order order = new Order();
        order.setId(orderId);
        order.setCustomerName("John Doe");

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        Optional<Order> foundOrder = orderService.getOrderById(orderId);

        assertTrue(foundOrder.isPresent());
        assertEquals("John Doe", foundOrder.get().getCustomerName());
        verify(orderRepository, times(1)).findById(orderId);
    }

    @Test
    void getAllOrders() {
        List<Order> orders = new ArrayList<>();
        Order order1 = new Order();
        order1.setCustomerName("John Doe");
        Order order2 = new Order();
        order2.setCustomerName("Jane Doe");
        orders.add(order1);
        orders.add(order2);

        when(orderRepository.findAll()).thenReturn(orders);

        List<Order> foundOrders = orderService.getAllOrders();

        assertEquals(2, foundOrders.size());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void updateOrder() {
        Long orderId = 1L;
        Order existingOrder = new Order();
        existingOrder.setId(orderId);
        existingOrder.setCustomerName("Old Name");

        Order updatedOrderDetails = new Order();
        updatedOrderDetails.setCustomerName("New Name");

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(existingOrder));
        when(orderRepository.save(existingOrder)).thenReturn(existingOrder);

        Order updatedOrder = orderService.updateOrder(orderId, updatedOrderDetails);

        assertNotNull(updatedOrder);
        assertEquals("New Name", updatedOrder.getCustomerName());
        verify(orderRepository, times(1)).findById(orderId);
        verify(orderRepository, times(1)).save(existingOrder);
    }

    @Test
    void deleteOrder() {
        Long orderId = 1L;
        doNothing().when(orderRepository).deleteById(orderId);

        orderService.deleteOrder(orderId);

        verify(orderRepository, times(1)).deleteById(orderId);
    }
}
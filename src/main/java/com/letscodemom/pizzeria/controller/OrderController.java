package com.letscodemom.pizzeria.controller;

import com.letscodemom.pizzeria.persistence.entity.OrderEntity;
import com.letscodemom.pizzeria.persistence.projection.OrderSummary;
import com.letscodemom.pizzeria.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/today")
    public ResponseEntity<List<OrderEntity>> getTodayOrders() {
        return ResponseEntity.ok(orderService.getTodayOrders());
    }

    @GetMapping("/outside")
    public ResponseEntity<List<OrderEntity>> getOutSide() {
        return ResponseEntity.ok(orderService.getOutSideOrders());
    }

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<List<OrderEntity>> getCustomerOrders(@PathVariable String customerId) {
        return ResponseEntity.ok(orderService.getCustomerOrders(customerId));
    }

    @GetMapping("/summary/{idOrder}")
    public ResponseEntity<OrderSummary>  getOrderSummaryByIdOrder(@PathVariable int idOrder) {
        return ResponseEntity.ok(orderService.getOrderSummaryByIdOrder(idOrder));
    }
}

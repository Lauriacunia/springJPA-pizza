package com.letscodemom.pizzeria.service;

import com.letscodemom.pizzeria.persistence.entity.OrderEntity;
import com.letscodemom.pizzeria.persistence.projection.OrderSummary;
import com.letscodemom.pizzeria.persistence.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {
    private static final String CARRYOUT = "C";
    private static final String DELIVERY = "D";
    private static final String ON_SITE = "S";

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<OrderEntity> getOrdersAfterDate(LocalDateTime date) {
        return orderRepository.findAllByDateAfter(date);
    }

//    public List<OrderEntity> getOrdersBeforeDate(LocalDateTime date) {
//        return orderRepository.findAllByDateBefore(date);
//    }

    public List<OrderEntity> getTodayOrders() {
        return orderRepository.findAllByDateAfter(LocalDate.now().atStartOfDay());
    }

    public List<OrderEntity> getOutSideOrders() {
        List<String> types = Arrays.asList(DELIVERY, CARRYOUT);
        return orderRepository.findAllByMethodIn(types);
    }

    public List<OrderEntity> getCustomerOrders(String customerId) {
        return orderRepository.findCustomerOrders(customerId);
    }

    public OrderSummary getOrderSummaryByIdOrder(int idOrder) {
        return orderRepository.getOrderSummaryByIdOrder(idOrder);
    }

}

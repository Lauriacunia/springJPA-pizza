package com.letscodemom.pizzeria.persistence.repository;

import com.letscodemom.pizzeria.persistence.entity.OrderEntity;
import com.letscodemom.pizzeria.persistence.projection.OrderSummary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer>{
    List<OrderEntity> findAllByDateAfter(LocalDateTime date);

    List<OrderEntity> findAllByMethodIn(List<String> types);
    //SQL nativo
    @Query(value = "SELECT * FROM pizza_order WHERE id_customer = :customerId", nativeQuery = true)
    List<OrderEntity> findCustomerOrders(@Param("customerId") String customerId);

    @Query(value ="SELECT\n" +
            "    po.id_order AS idOrder,\n" +
            "    cu.name AS customerName,\n" +
            "    po.date AS orderDate,\n" +
            "    po.total,\n" +
            "    GROUP_CONCAT(pi.name) AS pizzaNames\n" +
            "FROM\n" +
            "    pizza_order po\n" +
            "        INNER JOIN customer cu ON po.id_customer = cu.id_customer\n" +
            "        INNER JOIN order_item oi ON po.id_order = oi.id_order\n" +
            "        INNER JOIN pizza pi ON oi.id_pizza = pi.id_pizza\n" +
            "WHERE\n" +
            "        po.id_order = :idOrder\n" +
            "GROUP BY\n" +
            "    po.id_order,\n" +
            "    cu.name,\n" +
            "    po.date,\n" +
            "    po.total", nativeQuery = true)
    OrderSummary getOrderSummaryByIdOrder(@Param("idOrder") int idOrder);
}

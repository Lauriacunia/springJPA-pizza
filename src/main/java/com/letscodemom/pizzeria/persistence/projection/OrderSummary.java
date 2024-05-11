package com.letscodemom.pizzeria.persistence.projection;

import java.time.LocalDateTime;

public interface OrderSummary {
    //no hace falta que coincidan con el nombre de las columnas
    // es un DTO para mostrar solo los datos que necesitamos (de diferentes tablas)
    Integer getIdOrder(); //OrderEntity
    String getCustomerName(); //CustomerEntity
    LocalDateTime getOrderDate(); //OrderEntity
    Double getTotal(); //OrderEntity
    String getPizzaNames(); //OrderItemEntity -> PizzaEntity
}

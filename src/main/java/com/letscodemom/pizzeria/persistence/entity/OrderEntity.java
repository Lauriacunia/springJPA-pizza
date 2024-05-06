package com.letscodemom.pizzeria.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="pizza_order")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_order", nullable = false)
    private Integer idOrder;

    @Column(name="id_customer", nullable = false)
    private String idCustomer;

    @Column(nullable = false, columnDefinition ="DATETIME" )
    private LocalDateTime date;

    @Column(nullable = false, columnDefinition = "DECIMAL(5,2)")
    private Double total;

    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private String method;

    @Column(name= "additional_notes", length = 200)
    private String additionalNotes;





}

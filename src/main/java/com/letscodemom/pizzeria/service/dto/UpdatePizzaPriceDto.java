package com.letscodemom.pizzeria.service.dto;

import lombok.Data;

@Data // Lombok annotation to generate getters and setters
public class UpdatePizzaPriceDto {

    private int pizzaId;
    private double newPrice;
}

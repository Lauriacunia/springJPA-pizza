package com.letscodemom.pizzeria.persistence.repository;

import com.letscodemom.pizzeria.service.dto.UpdatePizzaPriceDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import com.letscodemom.pizzeria.persistence.entity.PizzaEntity;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {
    List<PizzaEntity> findAllByAvailableTrueOrderByPriceAsc();
    PizzaEntity findAllByAvailableTrueAndNameIgnoreCase(String name);
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);
    Integer countByVeganTrue();
    Optional<PizzaEntity> findFirstByName(String name);
    List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceDesc(double price);

    @Query(value = "UPDATE PizzaEntity p SET p.price = :#{#updatePizzaPriceDto.newPrice} WHERE p.id = :#{#updatePizzaPriceDto.pizzaId}")
    @Modifying
    void updatePrice(@Param("updatePizzaPriceDto")UpdatePizzaPriceDto updatePizzaPriceDto);


}

package com.letscodemom.pizzeria.persistence.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.letscodemom.pizzeria.persistence.entity.PizzaEntity;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {
    List<PizzaEntity> findAllByAvailableTrueOrderByPriceAsc();
    PizzaEntity findAllByAvailableTrueAndNameIgnoreCase(String name);
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);
    Integer countByVeganTrue();
    Optional<PizzaEntity> findFirstByName(String name);
    List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceDesc(double price);


}

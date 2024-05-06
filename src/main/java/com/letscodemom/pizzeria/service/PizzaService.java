package com.letscodemom.pizzeria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.letscodemom.pizzeria.persistence.entity.PizzaEntity;

import java.util.List;

@Service
public class PizzaService {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PizzaService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<PizzaEntity> getPizzas() {
        return jdbcTemplate.query("SELECT * FROM pizza", new BeanPropertyRowMapper<>(PizzaEntity.class));
    }

}

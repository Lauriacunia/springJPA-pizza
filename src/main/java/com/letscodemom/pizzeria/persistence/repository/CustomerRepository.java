package com.letscodemom.pizzeria.persistence.repository;

import com.letscodemom.pizzeria.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository {
    // JPQL query to find a customer by phone number
    @Query("SELECT c FROM CustomerEntity c WHERE c.phone = :phone")
    CustomerEntity findByPhone(@Param("phone") String phone);
}

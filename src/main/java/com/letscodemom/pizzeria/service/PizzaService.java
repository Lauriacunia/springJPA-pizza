package com.letscodemom.pizzeria.service;

import com.letscodemom.pizzeria.persistence.repository.PizzaPageAndSortingRepository;
import com.letscodemom.pizzeria.service.dto.UpdatePizzaPriceDto;
import com.letscodemom.pizzeria.service.exception.EmailApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.letscodemom.pizzeria.persistence.entity.PizzaEntity;
import com.letscodemom.pizzeria.persistence.repository.PizzaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PizzaService {

    /* Example con JdbcTemplate */
    /*
     * private JdbcTemplate jdbcTemplate;
     * 
     * @Autowired
     * public PizzaService(JdbcTemplate jdbcTemplate) {
     * this.jdbcTemplate = jdbcTemplate;
     * }
     * 
     * public List<PizzaEntity> getPizzas() {
     * return jdbcTemplate.query("SELECT * FROM pizza", new
     * BeanPropertyRowMapper<>(PizzaEntity.class));
     * }
     */

    /* Example con JPA */
    private final PizzaRepository pizzaRepository;
    private final PizzaPageAndSortingRepository pizzaPageAndSortingRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, PizzaPageAndSortingRepository pizzaPageAndSortingRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaPageAndSortingRepository = pizzaPageAndSortingRepository;
    }

    public List<PizzaEntity> getPizzas() {
       return pizzaRepository.findAll(); // metodo de JPA
    }
    public Page<PizzaEntity> getPizzasPageable(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PizzaEntity> pizzaPage = pizzaPageAndSortingRepository.findAll(pageable);
        return pizzaPage;

    }
    public Page<PizzaEntity> getPizzasAvailable(int page, int size, String sortBy, String direction) {
        //direction es un string que puede ser "asc" o "desc"
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return pizzaPageAndSortingRepository.findAllByAvailableTrue(pageable); // metodos de JPA
    }

//    public List<PizzaEntity> getPizzasAvailable() {
//        return pizzaRepository.findAllByAvailableTrueOrderByPriceAsc(); // metodos de JPA
//    }

    public PizzaEntity get(int idPizza) {
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }

    public PizzaEntity getByName(String name) {
        return this.pizzaRepository.findAllByAvailableTrueAndNameIgnoreCase(name);
    }

    public PizzaEntity getFirstByName(String name) {
        return this.pizzaRepository.findFirstByName(name).orElse(null);
    }

    public List<PizzaEntity> getByDescription(String description) {
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(description);
    }

    public List<PizzaEntity> getTop3ByPrice(double price) {
        return this.pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceDesc(price);
    }

    public Integer countVegan() {
        return this.pizzaRepository.countByVeganTrue();
    }

    public PizzaEntity save(PizzaEntity pizza) {
        return this.pizzaRepository.save(pizza);
    }

    public PizzaEntity update(PizzaEntity pizza) {
        return this.pizzaRepository.save(pizza); // utiliza el mismo metodo que create entity
    }

    public void delete(int idPizza) {
        this.pizzaRepository.deleteById(idPizza);
    }

    public boolean exists (int idPizza) {
        return this.pizzaRepository.existsById(idPizza);
    }

    @Transactional(noRollbackFor = EmailApiException.class,
    propagation = Propagation.REQUIRES_NEW
    ) // si quiero que NO se haga rollback en caso de error particular
    public void updatePrice(UpdatePizzaPriceDto updatePizzaPriceDto) {
        this.pizzaRepository.updatePrice(updatePizzaPriceDto);
        // @Transactional hace que si hay un error en el metodo, se haga un rollback del update
       // this.sendEmail();
    }

    private void sendEmail() {
        // forzamos el error para probar
        throw new EmailApiException("Error al enviar email");
    }

}

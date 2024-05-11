package com.letscodemom.pizzeria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.letscodemom.pizzeria.persistence.entity.PizzaEntity;
import com.letscodemom.pizzeria.service.PizzaService;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {
    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<Page<PizzaEntity>> getAll(@RequestParam(defaultValue = "0" ) int page,
                                                    @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(this.pizzaService.getPizzasPageable(page, size));
    }

//    @GetMapping
//    public ResponseEntity<List<PizzaEntity>> getAll() {
//        return ResponseEntity.ok(this.pizzaService.getPizzas());
//
//    }

    @GetMapping("/available")
    public ResponseEntity<Page<PizzaEntity>> getAllAvailable(
            @RequestParam(defaultValue = "0" ) int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "price") String sortBy,
            @RequestParam(defaultValue = "asc") String direction )
            {
        return ResponseEntity.ok(this.pizzaService.getPizzasAvailable(page, size, sortBy, direction));
    }
//    @GetMapping("/available")
//    public ResponseEntity<List<PizzaEntity>> getAllAvailable() {
//        return ResponseEntity.ok(this.pizzaService.getPizzasAvailable());
//    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PizzaEntity> getPizzaByName(@PathVariable String name) {
      //  return ResponseEntity.ok(this.pizzaService.getByName(name));
        return ResponseEntity.ok(this.pizzaService.getFirstByName(name));
    }

    @GetMapping("/price/{price}")
    public ResponseEntity<List<PizzaEntity>> getPizzasByPrice(@PathVariable double price) {
        return ResponseEntity.ok(this.pizzaService.getTop3ByPrice(price));
    }

    @GetMapping("/description/{description}")
    public ResponseEntity<List<PizzaEntity>> getPizzasByDescription(@PathVariable String description) {
        return ResponseEntity.ok(this.pizzaService.getByDescription(description));
    }

    @GetMapping("/vegan-qty")
    public ResponseEntity<Integer> countVegan() {
        return ResponseEntity.ok(this.pizzaService.countVegan());
    }

    @GetMapping("/{idPizza}")
    public ResponseEntity<PizzaEntity> getPizzaById(@PathVariable int idPizza) {
        return ResponseEntity.ok(this.pizzaService.get(idPizza));
    }

    @PostMapping
    public ResponseEntity<PizzaEntity> savePizza(@RequestBody PizzaEntity pizza) {
        if (pizza.getIdPizza() == null || !this.pizzaService.exists(pizza.getIdPizza())) {
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();  // Optional: Return Bad Request if needed
    }

    @PutMapping
    public ResponseEntity<PizzaEntity> updatePizza(@RequestBody PizzaEntity pizza) {
        if (pizza.getIdPizza() == null || !this.pizzaService.exists(pizza.getIdPizza())) {
            return ResponseEntity.badRequest().build();  // Optional: Return Bad Request if needed
        }
        return ResponseEntity.ok(this.pizzaService.update(pizza));
    }

    @DeleteMapping("/{idPizza}")
    public ResponseEntity<Void> deletePizza(@PathVariable int idPizza) {
        if(this.pizzaService.exists(idPizza)) {
            this.pizzaService.delete(idPizza);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();  // Optional: Return Bad Request if needed
    }
}
